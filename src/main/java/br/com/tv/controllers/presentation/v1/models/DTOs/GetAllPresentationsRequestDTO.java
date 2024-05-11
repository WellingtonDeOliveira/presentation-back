package br.com.tv.controllers.presentation.v1.models.DTOs;

import br.com.base.shared.models.DTOs.PageableFilter;
import br.com.base.shared.models.enums.SortDirection;
import br.com.tv.controllers.presentation.v1.models.enums.GetPresentationOfColumns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "GetAllPresentationsRequest")
public class GetAllPresentationsRequestDTO extends PageableFilter<GetPresentationOfColumns> {

    private String search;

    public GetAllPresentationsRequestDTO(Integer pageNumber, Integer pageSize, GetPresentationOfColumns sortBy, SortDirection sortDirection) {
        super(
                handleNullValue(pageNumber, 1),
                handleNullValue(pageSize, 10),
                handleNullValue(sortBy, GetPresentationOfColumns.createdAt),
                handleNullValue(sortDirection, SortDirection.DESC)
        );
    }
}
