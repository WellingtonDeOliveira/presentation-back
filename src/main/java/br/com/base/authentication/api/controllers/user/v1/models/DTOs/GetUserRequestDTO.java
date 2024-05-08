package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.authentication.api.controllers.user.v1.models.enums.GetUsersOfColumn;
import br.com.base.shared.models.DTOs.PageableFilter;
import br.com.base.shared.models.enums.SortDirection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "GetUsersRequest")
public final class GetUserRequestDTO extends PageableFilter<GetUsersOfColumn> {

    private String search;

    public GetUserRequestDTO(Integer pageNumber, Integer pageSize, GetUsersOfColumn sortBy,
                              SortDirection sortDirection) {
        super(handleNullValue(pageNumber, 1), handleNullValue(pageSize, 10),
                handleNullValue(sortBy, GetUsersOfColumn.username), handleNullValue(sortDirection, SortDirection.DESC));
    }
}
