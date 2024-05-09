package br.com.tv.controllers.tv.v1.models.enums;

import br.com.base.shared.models.enums.SortColumn;

public enum GetTvOfColumns implements SortColumn {
    id("id"),
    createdAt("createdAt"),
    name("name"),
    campus("campus");

    private final String column;

    GetTvOfColumns(String column) { this.column = column; }

    @Override
    public String getColumn() { return column; }
}
