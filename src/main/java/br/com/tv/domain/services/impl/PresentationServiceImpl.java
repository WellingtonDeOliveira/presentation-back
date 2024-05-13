package br.com.tv.domain.services.impl;

import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.utils.DateTimeUtil;
import br.com.base.shared.utils.StringUtil;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.*;
import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.PresentationEntity;
import br.com.tv.domain.models.entities.TvEntity;
import br.com.tv.domain.repositories.FilesRepository;
import br.com.tv.domain.repositories.PresentationRepository;
import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.services.PresentationService;
import br.com.tv.domain.validations.presentation.PresentationValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PresentationServiceImpl implements PresentationService {

    private final FilesRepository filesRepository;
    private final PresentationRepository presentationRepository;
    private final PresentationValidator presentationValidator;

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    @Transactional
    public void create(PresentationRequestDTO request) {
        UserEntity loggedUser = getLoggedUser();
        List<FilesEntity> entities = new ArrayList<>();
        try {
            String currentDate = DateTimeUtil.nowZoneUTC().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Path directoryPath = Paths.get(uploadDir, currentDate);

            if (!Files.exists(directoryPath))
                Files.createDirectories(directoryPath);

            PresentationEntity presentation = new PresentationEntity(
                    request.name(),
                    request.time(),
                    null,
                    request.deletedAt(),
                    TvEntity.builder().id(request.tvId()).build()
            );

            for (MultipartFile file : request.files()) {
                String name = file.getOriginalFilename();
                String extension = Objects.requireNonNull(name).substring(name.lastIndexOf('.'));
                String ref = UUID.randomUUID() + "_" + file.getName() + extension;

                presentationValidator.validateForExtensions(extension);

                // Fazer o front escolher se é video ou imagem e tirar essa linha.
                presentation.setType(extension.equals(".mp4") ? "video" : "imagem");

                Files.copy(file.getInputStream(), directoryPath.resolve(ref), StandardCopyOption.REPLACE_EXISTING);
                entities.add(FilesEntity
                        .builder()
                        .name(name)
                        .presentation(presentation)
                        .user(UserEntity.builder().id(loggedUser.getId()).build())
                        .ref(ref)
                        .type(file.getContentType())
                        .build()
                );
            }
            presentationRepository.save(presentation);
            filesRepository.saveAll(entities);
        } catch (Exception e) {
            deleteByRefNames(entities.stream().map(FilesEntity::getRef).toList());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public GetAllPresentationsResponseDTO search(GetAllPresentationsRequestDTO request) {
        var pageable = request.buildPageable();
        var page = presentationRepository.search(StringUtil.like(request.getSearch()), pageable);

        return parseToPresentationPageableResultDTO(page);
    }

    @Override
    public GetPresentationResponseDTO getById(UUID id) {
        PresentationEntity presentation = presentationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apresentação não encontrada!"));
        try {
            return GetPresentationResponseDTO.builder()
                    .id(id)
                    .tvId(presentation.getTv().getId())
                    .deletedAt(presentation.getDeletedAt())
                    .updatedAt(presentation.getUpdatedAt())
                    .createdAt(presentation.getCreatedAt())
                    .name(presentation.getName())
                    .type(presentation.getType())
                    .files(getFilePathByDateAndName(filesRepository.findByPresentationId(presentation.getId())))
                    .build();
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(UUID id, UpdateNamePresentationRequestDTO request) {
        PresentationEntity presentation = presentationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apresentação não encontrada!"));
            System.out.println(request.name());
            presentation.setName(request.name());
            presentation.setUpdatedAt(DateTimeUtil.nowZoneUTC());
            presentationRepository.save(presentation);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        List<FilesEntity> files = filesRepository.findByPresentationId(id);
        files.forEach(f -> {
            try {
                Files.delete(Paths.get(uploadDir + f.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "/", f.getRef()));
            } catch (IOException e) {
                throw new BusinessException("Erro ao deletar anexo(s): " + f.getName());
            }
        });

        filesRepository.deleteAllByPresentationId(id);
        presentationRepository.deleteById(id);
    }

    @Override
    public void deleteByRefNames(List<String> names) {
        try {
            for (String name : names) {
                Files.delete(getFilePathByDateAndFilename(DateTimeUtil.nowZoneUTC(), name));
                names.remove(name);
            }
        } catch (Exception e) {
            var message = "Erro ao deletar anexo(s): ";
            throw new BusinessException(message + String.join(",", names));
        }
    }

    @Override
    @Transactional
    public void deleteByDate() {
        List<PresentationEntity> presentations = presentationRepository.findAllByDeletedAtBefore(DateTimeUtil.nowZoneUTC());
        presentations.forEach(presentation -> {
            List<FilesEntity> files = filesRepository.findByPresentationId(presentation.getId());
            files.forEach(f -> {
                try {
                    Files.delete(Paths.get(uploadDir + f.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "/", f.getRef()));
                } catch (IOException e) {
                    throw new BusinessException("Erro ao deletar anexo(s): " + f.getName());
                }
            });
            filesRepository.deleteAllByPresentationId(presentation.getId());
        });
        presentationRepository.deleteAll(presentations);
    }

    @Transactional
    private GetAllPresentationsResponseDTO parseToPresentationPageableResultDTO(Page<PresentationEntity> result) {
        List<GetAllPresentationsRecordDTO> content = result.getContent().stream()
                .map(presentation -> {
                    return GetAllPresentationsRecordDTO.builder()
                            .id(presentation.getId())
                            .tvId(presentation.getTv().getId())
                            .deletedAt(presentation.getDeletedAt())
                            .createdAt(presentation.getCreatedAt())
                            .name(presentation.getName())
                            .type(presentation.getType())
                            .build();
                }).toList();
        var page = new PageImpl<>(content, result.getPageable(), result.getTotalElements());
        return new GetAllPresentationsResponseDTO(page);
    }

    private List<GetFileResponseDTO> getFilePathByDateAndName(List<FilesEntity> files) throws FileNotFoundException {
        List<GetFileResponseDTO> filesResponse = new ArrayList<>();
        files.forEach(f ->
                {
                    try {
                        filesResponse.add(
                                GetFileResponseDTO.builder()
                                        .id(f.getId())
                                        .ref(f.getRef())
                                        .name(f.getName())
                                        .createdAt(f.getCreatedAt())
                                        .type(f.getType())
                                        .file(Files.readAllBytes(Paths.get(uploadDir + f.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "/", f.getRef())))
                                        .build()
                        );
                    } catch (IOException e) {
                        throw new BusinessException("Arquivo não encontrado: " + f.getRef());
                    }
                }
        );
        return filesResponse;
    }

    private Path getFilePathByDateAndFilename(OffsetDateTime pathDate, String refName) throws FileNotFoundException {
        String date = pathDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Path path = Paths.get(uploadDir + date + "/", refName);
        if (Files.exists(path))
            return path;

        var message = "Arquivo não existe: ";
        throw new FileNotFoundException(message + refName);
    }

    @SuppressWarnings("unchecked")
    private UserEntity getLoggedUser() {
        return ((Optional<UserEntity>) SecurityContextHolder.getContext().getAuthentication().getDetails()).get();
    }
}