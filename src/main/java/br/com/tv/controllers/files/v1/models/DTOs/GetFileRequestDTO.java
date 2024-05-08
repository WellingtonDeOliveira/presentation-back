package br.com.tv.controllers.files.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableFilter;
import br.com.base.shared.models.enums.SortDirection;
import br.com.tv.controllers.files.v1.models.enums.GetFileOfColumns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "GetFileRequest")
public final class GetFileRequestDTO extends PageableFilter<GetFileOfColumns> {

    private String search;

    public GetFileRequestDTO(Integer pageNumber, Integer pageSize, GetFileOfColumns sortBy, SortDirection sortDirection) {
        super(
                handleNullValue(pageNumber, 1),
                handleNullValue(pageSize, 10),
                handleNullValue(sortBy, GetFileOfColumns.createdAt),
                handleNullValue(sortDirection, SortDirection.DESC)
        );
    }
}
