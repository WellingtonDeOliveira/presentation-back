package br.com.tv.controllers.presentation.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetAllPresentationsResponseDTO")
public class GetAllPresentationsResponseDTO extends PageableResult<GetAllPresentationsRecordDTO> {
    public GetAllPresentationsResponseDTO(PageImpl<GetAllPresentationsRecordDTO> page) {
        super(page);
    }
}