package br.com.tv.controllers.presentation.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.controllers.files.v1.annotations.CreateFileEndpoint;
import br.com.tv.controllers.presentation.v1.annotations.*;
import br.com.tv.controllers.presentation.v1.models.DTOs.*;
import br.com.tv.domain.services.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@OpenApiController(name = "Presentation")
@AllArgsConstructor
@ApiController(path = "/v1/presentation")
public class PresentationController {

    private final PresentationService presentationService;

    @GetAllPresentationsEndpoint
    public ResponseEntity<GetAllPresentationsResponseDTO> search(@ModelAttribute GetAllPresentationsRequestDTO request) {
        var response = presentationService.search(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetPresentationEndpoint
    public ResponseEntity<GetPresentationResponseDTO> getById(@PathVariable("id") UUID id) {
        var response = presentationService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CreatePresentationEndpoint
    public ResponseEntity<Void> createPresentation(@ModelAttribute PresentationRequestDTO request) {
        presentationService.create(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @UpdatePresentationEndpoint
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @ModelAttribute UpdateNamePresentationRequestDTO request) {
        presentationService.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeletePresentationEndpoint
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        presentationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
