package br.com.tv.domain.services;

import br.com.tv.controllers.presentation.v1.models.DTOs.GetPresentationRequestDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.GetPresentationResponseDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.PresentationRequestDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.UpdateNamePresentationRequestDTO;

import java.util.List;
import java.util.UUID;

public interface PresentationService {

    void create(PresentationRequestDTO request);

    GetPresentationResponseDTO search(GetPresentationRequestDTO request);

    void update(UUID id, UpdateNamePresentationRequestDTO request);

    void delete(UUID id);

    void deleteByRefNames(List<String> names);
}
