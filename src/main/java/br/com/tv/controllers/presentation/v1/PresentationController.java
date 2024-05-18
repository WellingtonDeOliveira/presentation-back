package br.com.tv.controllers.presentation.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.controllers.presentation.v1.annotations.*;
import br.com.tv.controllers.presentation.v1.models.DTOs.*;
import br.com.tv.domain.services.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
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
    public ResponseEntity<Void> createPresentation(@ModelAttribute PresentationRequestDTO request) throws IOException {
        presentationService.create(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @UpdatePresentationNameEndpoint
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @ModelAttribute UpdateNamePresentationRequestDTO request) {
        presentationService.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeletePresentationEndpoint
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        presentationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteTvByPresentationEndpoint
    public ResponseEntity<Void> deleteTvByPresentation(@PathVariable("presentationId") UUID presentationId, @RequestBody RequestIdTvDTO tv) throws Exception {
        presentationService.deleteTvByPresentation(presentationId, tv.id());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @UpdatePresentationTvEndpoint
    public ResponseEntity<Void> updatedTvPresentation(@PathVariable("presentationId") UUID presentationId, @RequestBody RequestIdsTvsDTO tv) {
        presentationService.updatedTvPresentation(presentationId, tv.ids());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
