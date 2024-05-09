package br.com.tv.controllers.presentation.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.controllers.files.v1.annotations.CreateFileEndpoint;
import br.com.tv.controllers.files.v1.models.DTOs.FileRequestDTO;
import br.com.tv.controllers.presentation.v1.models.DTOs.PresentationRequestDTO;
import br.com.tv.domain.services.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

@OpenApiController(name = "Presentation")
@AllArgsConstructor
@ApiController(path = "/v1/presentation")
public class PresentationController {

    private final PresentationService presentationService;

    @CreateFileEndpoint
    public ResponseEntity<Void> createPresentation(@ModelAttribute PresentationRequestDTO request) {
        presentationService.create(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
