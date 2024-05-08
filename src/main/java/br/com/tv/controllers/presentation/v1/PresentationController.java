package br.com.tv.controllers.presentation.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.domain.services.PresentationService;
import lombok.AllArgsConstructor;

@OpenApiController(name = "Presentation")
@AllArgsConstructor
@ApiController(path = "/v1/presentation")
public class PresentationController {

    private final PresentationService presentationService;
}
