package br.com.tv.domain.services;

import br.com.tv.controllers.files.v1.models.DTOs.FileRequestDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileRequestDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;

import java.util.List;
import java.util.UUID;

public interface FileService {

    void uploadToDirectory(UUID idPresentation, FileRequestDTO files);

    GetFileResponseDTO search(GetFileRequestDTO request);

    void delete(UUID id);

    void deleteByRefNames(List<String> names);
}
