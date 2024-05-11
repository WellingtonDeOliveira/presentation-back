package br.com.tv.domain.services;

import br.com.tv.controllers.presentation.v1.models.DTOs.*;

import java.util.List;
import java.util.UUID;

public interface PresentationService {

    void create(PresentationRequestDTO request);

    GetAllPresentationsResponseDTO search(GetAllPresentationsRequestDTO request);

    GetPresentationResponseDTO getById(UUID id);

    void update(UUID id, UpdateNamePresentationRequestDTO request);

    void delete(UUID id);

    void deleteByRefNames(List<String> names);
}
