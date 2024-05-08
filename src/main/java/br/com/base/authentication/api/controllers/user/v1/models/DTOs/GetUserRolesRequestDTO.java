package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.authentication.api.controllers.user.v1.models.enums.GetRolesOfUserColumn;
import br.com.base.shared.models.DTOs.PageableFilter;
import br.com.base.shared.models.enums.SortDirection;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetUserRolesRequest")
public final class GetUserRolesRequestDTO extends PageableFilter<GetRolesOfUserColumn> {

    public GetUserRolesRequestDTO(Integer pageNumber, Integer pageSize, GetRolesOfUserColumn sortBy, SortDirection sortDirection) {
        super(handleNullValue(pageNumber, 1), handleNullValue(pageSize, 10), handleNullValue(sortBy, GetRolesOfUserColumn.createdAt), handleNullValue(sortDirection, SortDirection.DESC));
    }
}
