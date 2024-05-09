package br.com.tv.controllers.tv.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableFilter;
import br.com.base.shared.models.enums.SortDirection;
import br.com.tv.controllers.files.v1.models.enums.GetFileOfColumns;
import br.com.tv.controllers.tv.v1.models.enums.GetTvOfColumns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "GetTvRequestDTO")
public final class GetTvRequestDTO extends PageableFilter<GetTvOfColumns> {

    private String search;

    public GetTvRequestDTO(Integer pageNumber, Integer pageSize, GetTvOfColumns sortBy, SortDirection sortDirection) {
        super(
                handleNullValue(pageNumber, 1),
                handleNullValue(pageSize, 10),
                handleNullValue(sortBy, GetTvOfColumns.createdAt),
                handleNullValue(sortDirection, SortDirection.DESC)
        );
    }
}
