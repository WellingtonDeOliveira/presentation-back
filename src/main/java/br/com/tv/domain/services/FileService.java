package br.com.tv.domain.services;

import br.com.tv.controllers.files.v1.models.DTOs.FileRequestDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFilesRequestDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFilesResponseDTO;
import br.com.tv.domain.models.entities.FilesEntity;

import java.util.List;
import java.util.UUID;

public interface FileService {

    void uploadToDirectory(UUID idPresentation, FileRequestDTO files);

    GetFilesResponseDTO search(GetFilesRequestDTO request);

    GetFileResponseDTO getById(UUID id);

    void delete(UUID id);

    void deleteByRefNames(List<String> names);
}
