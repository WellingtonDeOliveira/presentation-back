package br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableResult;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

@Schema(name = "GetGroupsRolesResponse")
public class GetGroupsRolesResponseDTO extends PageableResult<GetGroupsRolesRecordsDTO> {
    public GetGroupsRolesResponseDTO(PageImpl<GetGroupsRolesRecordsDTO> page) {
        super(page);
    }
}
