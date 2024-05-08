package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetUserResponse")
public class GetUserResponseDTO extends PageableResult<GetUserRecordsDTO> {
    public GetUserResponseDTO(PageImpl<GetUserRecordsDTO> page) {
        super(page);
    }
}
