package br.com.tv.controllers.tv.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.domain.services.TvService;
import lombok.AllArgsConstructor;

@OpenApiController(name = "Tv")
@AllArgsConstructor
@ApiController(path = "/v1/tv")
public class TvController {

    private final TvService tvService;
}
