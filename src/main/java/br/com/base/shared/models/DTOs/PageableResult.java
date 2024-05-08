package br.com.base.shared.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Schema(name = "PageableResult")
public abstract class PageableResult<T> {
    @Schema(description = "Total records.", example = "50")
    private final long totalElements;
    @Schema(description = "Total pages.", example = "2")
    private final int totalPages;
    @Schema(description = "Number of records.", example = "30")
    private final int numberElements;
    @Schema(description = "Records number for the page.", example = "30")
    private final int pageSize;
    @Schema(description = "Page number.", example = "1")
    private final int pageNumber;
    @Schema(description = "It is the last record.", example = "false")
    private final boolean last;
    @Schema(description = "It is the first record.", example = "true")
    private final boolean first;
    @Schema(description = "It is empty.", example = "false")
    private final boolean empty;
    @Schema(description = "List of records.")
    private final List<T> records;

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumberElements() {
        return numberElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isEmpty() {
        return empty;
    }

    public List<T> getRecords() {
        return records;
    }

    public PageableResult(PageImpl<T> page) {
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        numberElements = page.getNumberOfElements();
        pageSize = page.getSize();
        pageNumber = page.getNumber() + 1;
        last = page.isLast();
        first = page.isFirst();
        empty = page.isEmpty();
        records = page.getContent();
    }
}
