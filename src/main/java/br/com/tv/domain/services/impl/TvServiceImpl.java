package br.com.tv.domain.services.impl;

import br.com.base.shared.utils.StringUtil;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRecordsDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvRequestDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.GetTvResponseDTO;
import br.com.tv.domain.models.entities.TvEntity;
import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.services.TvService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TvServiceImpl implements TvService {

    private TvRepository tvRepository;

    @Override
    public GetTvResponseDTO search(GetTvRequestDTO request) {
        var pageable = request.buildPageable();
        var page = tvRepository.search(StringUtil.like(request.getSearch()), pageable);

        return parseToTvsPageableResultDTO(page);
    }

    @Transactional
    private GetTvResponseDTO parseToTvsPageableResultDTO(Page<TvEntity> result) {
        List<GetTvRecordsDTO> content = result.getContent().stream()
                .map(tv -> {
                        return GetTvRecordsDTO.builder()
                                .id(tv.getId())
                                .createdAt(tv.getCreatedAt())
                                .name(tv.getName())
                                .campus(tv.getCampus())
                                .build();
                }).toList();
        var page = new PageImpl<>(content, result.getPageable(), result.getTotalElements());
        return new GetTvResponseDTO(page);
    }
}
