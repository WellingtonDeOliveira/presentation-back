package br.com.base.shared.models.DTOs;

import br.com.base.shared.models.enums.SortColumn;
import br.com.base.shared.models.enums.SortDirection;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Schema(name = "PageableFilter")
public abstract class PageableFilter<TSortBy extends SortColumn> {
    @JsonProperty
    @Schema(description = "Page number", example = "1")
    private final Integer pageNumber;
    @JsonProperty
    @Schema(description = "Registration number for the page", example = "30")
    private final Integer pageSize;
    @JsonProperty
    @Schema(description = "Name of the sort field")
    private final TSortBy sortBy;
    @JsonProperty
    @Schema(description = "Direction of ordination")
    private final SortDirection sortDirection;

    public PageableFilter(Integer pageNumber, Integer pageSize, TSortBy sortBy, SortDirection sortDirection) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public Pageable buildPageable() {
        Sort sort = Sort.by(sortDirection.getDirection(), sortBy.getColumn());
        return PageRequest.of(pageNumber - 1, (pageSize != -1 ? pageSize : Integer.MAX_VALUE), sort);
    }

    protected static <T> T handleNullValue(T value, T $default) {
        return value != null ? value : $default;
    }
}
