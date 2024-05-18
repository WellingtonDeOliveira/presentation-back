package br.com.tv.controllers.tv.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.controllers.tv.v1.annotations.GetPresentationByTvEndpoint;
import br.com.tv.controllers.tv.v1.annotations.GetTvsEndpoint;
import br.com.tv.controllers.tv.v1.annotations.GetTvsWithoutPresentationEndpoint;
import br.com.tv.controllers.tv.v1.models.DTOs.GetPresentationByTvResponseDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRecordsDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRequestDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvResponseDTO;
import br.com.tv.domain.services.TvService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@OpenApiController(name = "TV")
@AllArgsConstructor
@ApiController(path = "/v1/tv")
public class TvController {

    private final TvService tvService;

    @GetTvsEndpoint
    public ResponseEntity<GetTvResponseDTO> search(@ModelAttribute GetTvRequestDTO request) {
        var response = tvService.search(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetPresentationByTvEndpoint
    public ResponseEntity<GetPresentationByTvResponseDTO> getPresentationByTv() {
        var response =  tvService.getPresentationByTv();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetTvsWithoutPresentationEndpoint
    public ResponseEntity<List<GetTvRecordsDTO>> getTvWitchoutPresentation() {
        var response = tvService.getTvWithoutPresentation();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
