package br.com.tv.domain.services;

import br.com.tv.controllers.files.v1.models.DTOs.GetFileRequestDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.GetPresentationRequestDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.GetPresentationResponseDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.PresentationRequestDTO;

import java.util.List;
import java.util.UUID;

public interface PresentationService {

    void create(PresentationRequestDTO request);

    GetPresentationResponseDTO search(GetPresentationRequestDTO request);

    void delete(UUID id);

    void deleteByRefNames(List<String> names);
}
