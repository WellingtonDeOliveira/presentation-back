package br.com.tv.domain.services;

import br.com.tv.controllers.presentation.v1.models.DTOs.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PresentationService {

    void create(PresentationRequestDTO request) throws IOException;

    GetAllPresentationsResponseDTO search(GetAllPresentationsRequestDTO request);

    GetPresentationResponseDTO getById(UUID id);

    void update(UUID id, UpdateNamePresentationRequestDTO request);

    void delete(UUID id);

    void deleteByDate();

    void deleteTvByPresentation(UUID presentationId, UUID tv) throws Exception;

    void updatedTvPresentation(UUID presentationId, Set<UUID> tv);

    void deleteByRefNames(List<String> names);
}
