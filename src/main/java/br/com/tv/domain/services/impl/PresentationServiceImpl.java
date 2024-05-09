package br.com.tv.domain.services.impl;

import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.utils.DateTimeUtil;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileRequestDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.PresentationRequestDTO;
import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.PresentationEntity;
import br.com.tv.domain.models.entities.TvEntity;
import br.com.tv.domain.repositories.FilesRepository;
import br.com.tv.domain.repositories.PresentationRepository;
import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.services.PresentationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
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
    private final TvRepository tvRepository;
    private final PresentationRepository presentationRepository;

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

                presentation.setType(extension.equals(".mp4") ? "video" : "imagem");

                Files.copy(file.getInputStream(), directoryPath.resolve(ref), StandardCopyOption.REPLACE_EXISTING);
                entities.add(FilesEntity
                        .builder()
                        .name(name)
                        .presentation(presentation)
                        .user(UserEntity.builder().id(loggedUser.getId()).build())
                        .ref(ref)
                        .deletedAt(request.deletedAt())
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
    public void search(GetFileRequestDTO request) {

    }

    @Override
    public void delete(UUID id) {

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