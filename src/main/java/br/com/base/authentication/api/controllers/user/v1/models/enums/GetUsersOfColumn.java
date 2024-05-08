package br.com.base.authentication.api.controllers.user.v1.models.enums;

import br.com.base.shared.models.enums.SortColumn;

public enum GetUsersOfColumn implements SortColumn {
    id("id"),
    createdAt("createdAt"),
    campus("campus"),
    updatedAt("updatedAt"),
    username("username");

    private final String column;

    GetUsersOfColumn(String column) {
        this.column = column;
    }

    @Override
    public String getColumn() {
        return column;
    }
}
