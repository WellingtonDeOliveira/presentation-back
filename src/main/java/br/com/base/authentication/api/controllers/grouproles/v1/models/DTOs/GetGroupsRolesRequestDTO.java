package br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs;

import br.com.base.authentication.api.controllers.grouproles.v1.models.enums.GetGroupsRolesColumn;
import br.com.base.shared.models.DTOs.PageableFilter;
import br.com.base.shared.models.enums.SortDirection;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetGroupsRolesRequest")
public final class GetGroupsRolesRequestDTO extends PageableFilter<GetGroupsRolesColumn> {

    public GetGroupsRolesRequestDTO(Integer pageNumber, Integer pageSize, GetGroupsRolesColumn sortBy, SortDirection sortDirection) {
        super(handleNullValue(pageNumber, 1), handleNullValue(pageSize, 10), handleNullValue(sortBy, GetGroupsRolesColumn.createdAt), handleNullValue(sortDirection, SortDirection.DESC));
    }
}
