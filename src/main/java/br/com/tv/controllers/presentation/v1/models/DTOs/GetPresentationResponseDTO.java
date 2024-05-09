package br.com.tv.controllers.presentation.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetPresentationResponseDTO")
public class GetPresentationResponseDTO extends PageableResult<GetPresentationRecordDTO> {
    public GetPresentationResponseDTO(PageImpl<GetPresentationRecordDTO> page) {
        super(page);
    }
}