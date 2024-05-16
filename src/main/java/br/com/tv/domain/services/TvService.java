package br.com.tv.domain.services;

import br.com.tv.controllers.tv.v1.models.DTOs.GetPresentationByTvResponseDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRecordsDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRequestDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvResponseDTO;

import java.util.List;

public interface TvService {

    GetTvResponseDTO search(GetTvRequestDTO request);

    GetPresentationByTvResponseDTO getPresentationByTv();

    List<GetTvRecordsDTO> getTvWithoutPresentation();
}
