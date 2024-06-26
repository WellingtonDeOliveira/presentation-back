package br.com.tv.domain.services.impl;

import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.utils.DateTimeUtil;
import br.com.tv.controllers.files.v1.models.DTOs.*;
import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.PresentationEntity;
import br.com.tv.domain.repositories.FilesRepository;
import br.com.tv.domain.repositories.PresentationRepository;
import br.com.tv.domain.services.FileService;
import br.com.base.shared.utils.StringUtil;
import br.com.tv.domain.validations.PresentationValidator;
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
public class FileServiceImpl implements FileService {

    private final FilesRepository filesRepository;
    private final PresentationRepository presentationRepository;
    private final PresentationValidator presentationValidator;

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public GetFilesResponseDTO search(GetFilesRequestDTO request) {
        var pageable = request.buildPageable();
        var page = filesRepository.search(StringUtil.like(request.getSearch()), pageable);

        return parseToFilePageableResultDTO(page);
    }

    @Override
    public GetFileResponseDTO getById(UUID id) {
        FilesEntity file = findById(id);
        PresentationEntity presentation = findPresentationById(file.getPresentation().getId());

        try {
            return GetFileResponseDTO.builder()
                    .id(file.getId())
                    .createdAt(file.getCreatedAt())
                    .name(file.getName())
                    .namePresentation(presentation.getName())
                    .ref(file.getRef())
                    .type(file.getType())
                    .file(Files.readAllBytes(getFilePathByDateAndName(file.getCreatedAt(), file.getRef())))
                    .build();
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    private GetFilesResponseDTO parseToFilePageableResultDTO(Page<FilesEntity> result) {
            List<GetFilesRecordsDTO> content = result.getContent().stream()
                    .map(file -> {
                        return GetFilesRecordsDTO.builder()
                                .id(file.getId())
                                .createdAt(file.getCreatedAt())
                                .name(file.getName())
                                .type(file.getType())
                                .build();
                    }).toList();
            var page = new PageImpl<>(content, result.getPageable(), result.getTotalElements());
            return new GetFilesResponseDTO(page);
    }

    private Path getFilePathByDateAndName(OffsetDateTime pathDate, String route) throws FileNotFoundException {
        String date = pathDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Path path = Paths.get(uploadDir + date + "/", route);
        if (Files.exists(path))
            return path;

        throw new FileNotFoundException("Arquivo não encontrado: " + route);
    }

    @Override
    @Transactional
    public void uploadToDirectory(UUID idPresentation, FileRequestDTO request) {
        UserEntity loggedUser = getLoggedUser();
        List<FilesEntity> entities = new ArrayList<>();
        PresentationEntity presentation = findPresentationById(idPresentation);

        try {
            String currentDate = DateTimeUtil.nowZoneUTC().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Path directoryPath = Paths.get(uploadDir, currentDate);

            if (!Files.exists(directoryPath))
                Files.createDirectories(directoryPath);

            for (MultipartFile file: request.files()) {
                String name = file.getOriginalFilename();
                String extension = Objects.requireNonNull(name).substring(name.lastIndexOf('.'));
                String ref = UUID.randomUUID() + "_" + file.getName() + extension;

                presentationValidator.validateForExtensions(extension);
                presentationValidator.validateForTypePresentation(extension, presentation);

                Files.copy(file.getInputStream(), directoryPath.resolve(ref), StandardCopyOption.REPLACE_EXISTING);
                entities.add(FilesEntity
                        .builder()
                        .name(name)
                        .presentation(PresentationEntity.builder().id(idPresentation).build())
                        .user(UserEntity.builder().id(loggedUser.getId()).build())
                        .ref(ref)
                        .type(extension)
                        .build()
                );
            }
            filesRepository.saveAll(entities);
        } catch (Exception e) {
            deleteByRefNames(entities.stream().map(FilesEntity::getRef).toList());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void delete(UUID fileId) {
        try {
            FilesEntity file = findById(fileId);
            Path path = getFilePathByDateAndFilename(file.getCreatedAt(), file.getRef());
            Files.delete(path);

            filesRepository.deleteById(fileId);
        } catch (Exception e) {
            throw new BusinessException("Erro ao deletar anexo(s): " + fileId);
        }
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

    private FilesEntity findById(UUID id) {
        return filesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Arquivo não encontrado: " + id));
    }

    private PresentationEntity findPresentationById(UUID id) {
        return presentationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apresentação não encontrado!"));
    }

    @SuppressWarnings("unchecked")
    private UserEntity getLoggedUser() {
        return ((Optional<UserEntity>) SecurityContextHolder.getContext().getAuthentication().getDetails()).get();
    }

    private Path getFilePathByDateAndFilename(OffsetDateTime pathDate, String refName) throws FileNotFoundException {
        String date = pathDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Path path = Paths.get(uploadDir + date + "/", refName);
        if (Files.exists(path))
            return path;

        var message = "Arquivo não existe: ";
        throw new FileNotFoundException(message + refName);
    }
}
