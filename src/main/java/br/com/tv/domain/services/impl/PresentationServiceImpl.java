package br.com.tv.domain.services.impl;

import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.utils.DateTimeUtil;
import br.com.base.shared.utils.StringUtil;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.*;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRecordsDTO;
import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.PresentationEntity;
import br.com.tv.domain.models.entities.PresentationLinkTvEntity;
import br.com.tv.domain.models.entities.TvEntity;
import br.com.tv.domain.repositories.FilesRepository;
import br.com.tv.domain.repositories.PresentationLinkTvRepository;
import br.com.tv.domain.repositories.PresentationRepository;
import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.services.PresentationService;
import br.com.tv.domain.validations.PresentationValidator;
import br.com.tv.domain.validations.TvValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PresentationServiceImpl implements PresentationService {

    private static final Logger logger = LoggerFactory.getLogger(PresentationService.class);

    private final FilesRepository filesRepository;
    private final PresentationRepository presentationRepository;
    private final TvRepository tvRepository;
    private final TvValidator tvValidator;
    private final PresentationValidator presentationValidator;
    private final PresentationLinkTvRepository presentationLinkTvRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    @Transactional
    public void create(PresentationRequestDTO request) throws IOException {
        UserEntity loggedUser = getLoggedUser();
        List<FilesEntity> entities = new ArrayList<>();
        List<TvEntity> tvs = tvRepository.findAllById(request.tvsId());
        String currentDate = DateTimeUtil.nowZoneUTC().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Path directoryPath = Paths.get(uploadDir, currentDate);
        PresentationEntity presentation = buildPresentation(request);

        tvs.forEach(tv -> tvValidator.thisIsWithoutPresententation(tv.getId()));
        tvValidator.listTvsIsEmpty(tvs);
        if (!Files.exists(directoryPath)) Files.createDirectories(directoryPath);

        entities.addAll(uploadAndCreateEntityFile(request.files(), entities, presentation, directoryPath, loggedUser));

        presentationRepository.save(presentation);
        filesRepository.saveAll(entities);
        addTvs(presentation, tvs);
    }

    @Transactional
    private List<FilesEntity> uploadAndCreateEntityFile(List<MultipartFile> files,
                                                        List<FilesEntity> entities,
                                                        PresentationEntity presentation,
                                                        Path directoryPath,
                                                        UserEntity loggedUser) {
        files.forEach(file -> {
            String name = file.getOriginalFilename();
            String extension = Objects.requireNonNull(name).substring(name.lastIndexOf('.'));
            String ref = UUID.randomUUID() + "_" + file.getName() + extension;

            presentationValidator.validateForExtensions(extension);

            // Fazer o front escolher se é video ou imagem e tirar essa linha.
            presentation.setType(extension.equals(".mp4") ? "video" : "imagem");
            try {
                Files.copy(file.getInputStream(), directoryPath.resolve(ref), StandardCopyOption.REPLACE_EXISTING);
                entities.add(FilesEntity
                        .builder()
                        .name(name)
                        .presentation(presentation)
                        .user(UserEntity.builder().id(loggedUser.getId()).build())
                        .ref(ref)
                        .type(extension)
                        .build()
                );
            } catch (Exception e) {
                deleteByRefNames(entities.stream().map(FilesEntity::getRef).toList());
                throw new BusinessException(e.getMessage());
            }
        });
        return entities;
    }

    @Override
    public GetAllPresentationsResponseDTO search(GetAllPresentationsRequestDTO request) {
        var pageable = request.buildPageable();
        var page = presentationRepository.search(StringUtil.like(request.getSearch()), pageable);

        return parseToPresentationPageableResultDTO(page);
    }

    @Override
    public GetPresentationResponseDTO getById(UUID id) {
        PresentationEntity presentation = findById(id);
        try {
            return GetPresentationResponseDTO.builder()
                    .id(id)
                    .tvs(getTvRecordsDTOS(presentation))
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
        PresentationEntity presentation = findById(id);
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
        presentationLinkTvRepository.deleteAllByPresentationId(id);
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
            presentationLinkTvRepository.deleteAllByPresentationId(presentation.getId());
        });
        presentationRepository.deleteAll(presentations);
    }

    @Override
    @Transactional
    public void deleteTvByPresentation(UUID presentationId, UUID tvId) throws Exception {
        logger.info("Deleting TV with ID {} from presentation with ID {}", tvId, presentationId);
        try {
            presentationLinkTvRepository.deleteByPresentationIdAndTvId(presentationId, tvId);
            logger.info("Deletion completed");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updatedTvPresentation(UUID presentationId, Set<UUID> tvId) {
        List<TvEntity> tvs = tvRepository.findAllById(tvId);
        tvs.forEach(tv -> tvValidator.thisIsWithoutPresententation(tv.getId()));
        tvValidator.listTvsIsEmpty(tvs);

        addTvs(findById(presentationId), tvs);
    }

    private List<GetTvRecordsDTO> getTvRecordsDTOS(PresentationEntity presentation) {
        return presentation.getTvs().stream().map(linkTv -> {
            TvEntity tv = linkTv.getTv();
            return GetTvRecordsDTO.builder()
                    .id(tv.getId())
                    .campus(tv.getCampus())
                    .name(tv.getName())
                    .createdAt(tv.getCreatedAt())
                    .build();
        }).toList();
    }

    @Transactional
    private GetAllPresentationsResponseDTO parseToPresentationPageableResultDTO(Page<PresentationEntity> result) {
        List<GetAllPresentationsRecordDTO> content = result.getContent().stream()
                .map(presentation -> {
                    return GetAllPresentationsRecordDTO.builder()
                            .id(presentation.getId())
                            .tvs(getTvRecordsDTOS(presentation))
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

    @Transactional
    private void addTvs(PresentationEntity presentation, List<TvEntity> tvs) {
        List<PresentationLinkTvEntity> links = new ArrayList<>();
        tvs = removeDuplicateRoles(tvs);

        tvs.forEach(tv -> links.add(new PresentationLinkTvEntity(presentation, tv)));

        presentationLinkTvRepository.saveAll(links);
    }

    private List<TvEntity> removeDuplicateRoles(List<TvEntity> tvs) {
        return tvs.stream().distinct().collect(Collectors.toList());
    }

    private PresentationEntity buildPresentation(PresentationRequestDTO request) {
        return PresentationEntity.builder()
                .name(request.name())
                .time(request.time())
                .deletedAt(request.deletedAt())
                .build();
    }

    private Path getFilePathByDateAndFilename(OffsetDateTime pathDate, String refName) throws FileNotFoundException {
        String date = pathDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Path path = Paths.get(uploadDir + date + "/", refName);
        if (Files.exists(path))
            return path;

        var message = "Arquivo não existe: ";
        throw new FileNotFoundException(message + refName);
    }

    private PresentationEntity findById(UUID id) {
        return presentationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apresentação não encontrada!"));
    }

    @SuppressWarnings("unchecked")
    private UserEntity getLoggedUser() {
        return ((Optional<UserEntity>) SecurityContextHolder.getContext().getAuthentication().getDetails()).get();
    }
}