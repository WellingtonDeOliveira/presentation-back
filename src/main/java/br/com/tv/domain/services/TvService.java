package br.com.tv.domain.services;

import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRequestDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvResponseDTO;

public interface TvService {

    GetTvResponseDTO search(GetTvRequestDTO request);
}
