package br.com.tv.controllers.tv.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetTVResponseDTO")
public class GetTvResponseDTO extends PageableResult<GetTvRecordsDTO> {
    public GetTvResponseDTO(PageImpl<GetTvRecordsDTO> page) {
        super(page);
    }
}
