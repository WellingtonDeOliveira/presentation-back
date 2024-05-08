package br.com.base.authentication.api.controllers.grouproles.v1.models.enums;

import br.com.base.shared.models.enums.SortColumn;

public enum GetGroupsRolesColumn implements SortColumn {
    id("id"),
    createdAt("createdAt"),
    updatedAt("updatedAt"),
    name("name");

    private final String column;

    GetGroupsRolesColumn(String column) {
        this.column = column;
    }

    @Override
    public String getColumn() {
        return column;
    }
}
