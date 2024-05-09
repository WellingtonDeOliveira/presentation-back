package br.com.tv.controllers.presentation.v1.models.enums;

import br.com.base.shared.models.enums.SortColumn;

public enum GetPresentationOfColumns implements SortColumn {
    id("id"),
    createdAt("createdAt"),
    name("name"),
    campus("campus");

    private final String column;

    GetPresentationOfColumns(String column) { this.column = column; }

    @Override
    public String getColumn() { return column; }
}
