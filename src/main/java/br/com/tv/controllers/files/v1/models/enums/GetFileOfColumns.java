package br.com.tv.controllers.files.v1.models.enums;

import br.com.base.shared.models.enums.SortColumn;

public enum GetFileOfColumns implements SortColumn {
    id("id"),
    createdAt("createdAt"),
    name("name");

    private final String column;

    GetFileOfColumns(String column) { this.column = column; }

    @Override
    public String getColumn() { return column; }
}
