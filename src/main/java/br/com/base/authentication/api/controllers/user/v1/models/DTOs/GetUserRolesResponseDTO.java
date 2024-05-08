package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetUserRolesResponse")
public class GetUserRolesResponseDTO extends PageableResult<GetUserRolesRecordsDTO> {
    public GetUserRolesResponseDTO(PageImpl<GetUserRolesRecordsDTO> page) {
        super(page);
    }
}
