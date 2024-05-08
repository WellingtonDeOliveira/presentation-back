package br.com.base.authentication.api.controllers.user.v1.models.enums;

import br.com.base.shared.models.enums.SortColumn;

public enum GetRolesOfUserColumn implements SortColumn {
    id("id"),
    createdAt("createdAt"),
    updatedAt("updatedAt"),
    campus("campus"),
    role("role");

    private final String column;

    GetRolesOfUserColumn(String column) {
        this.column = column;
    }

    @Override
    public String getColumn() {
        return column;
    }
}
