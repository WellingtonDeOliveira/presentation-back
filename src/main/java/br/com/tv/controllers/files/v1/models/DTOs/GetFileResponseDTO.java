package br.com.tv.controllers.files.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetFileResponseDTO")
public class GetFileResponseDTO extends PageableResult<GetFileRecordsDTO> {
    public GetFileResponseDTO(PageImpl<GetFileRecordsDTO> page) {
        super(page);
    }
}
