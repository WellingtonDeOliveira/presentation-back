package br.com.base.shared.models.enums;

public enum RoleType {
    ROLE_READ_ROLE(Roles.ROLE_READ_ROLE),
    ROLE_CREATE_USER(Roles.ROLE_CREATE_USER),
    ROLE_ADD_USER_GROUPS_ROLES(Roles.ROLE_ADD_USER_GROUPS_ROLES),
    ROLE_REMOVE_USER_GROUPS_ROLES(Roles.ROLE_REMOVE_USER_GROUPS_ROLES),
    ROLE_GET_USER_ROLES(Roles.ROLE_GET_USER_ROLES),
    ROLE_ADD_USER_ROLES(Roles.ROLE_ADD_USER_ROLES),
    ROLE_REMOVE_USER_ROLES(Roles.ROLE_REMOVE_USER_ROLES),
    ROLE_GET_GROUPS_ROLES(Roles.ROLE_GET_GROUPS_ROLES),
    ROLE_GET_GROUP_ROLES_DETAILS(Roles.ROLE_GET_GROUP_ROLES_DETAILS),
    ROLE_CREATE_GROUP_ROLES(Roles.ROLE_CREATE_GROUP_ROLES),
    ROLE_UPDATE_GROUP_ROLES(Roles.ROLE_UPDATE_GROUP_ROLES),
    ROLE_REMOVE_GROUP_ROLES(Roles.ROLE_REMOVE_GROUP_ROLES),
    ROLE_ADD_GROUP_ROLES_ROLES(Roles.ROLE_ADD_GROUP_ROLES_ROLES),
    ROLE_REMOVE_GROUP_ROLES_ROLES(Roles.ROLE_REMOVE_GROUP_ROLES_ROLES),
    ROLE_GET_AVAILABLE_PERMISSIONS(Roles.ROLE_GET_AVAILABLE_PERMISSIONS),
    ROLE_CREATE_LICENSES(Roles.ROLE_CREATE_LICENSES),
    ROLE_GET_LICENSES(Roles.ROLE_GET_LICENSES),
    ROLE_GET_LICENSE_DETAILS(Roles.ROLE_GET_LICENSE_DETAILS),
    ROLE_DOWNLOAD_LICENSE(Roles.ROLE_DOWNLOAD_LICENSE),
    ROLE_GET_USERS(Roles.ROLE_GET_USERS),
    ROLE_UPDATED_PASSWORD(Roles.ROLE_UPDATED_PASSWORD),
    ROLE_CREATED_FILE(Roles.ROLE_CREATED_FILE),
    ROLE_GET_FILE(Roles.ROLE_GET_FILE),
    ROLE_DELETED_FILE(Roles.ROLE_DELETED_FILE),
    ROLE_GET_ALL_FILES(Roles.ROLE_GET_ALL_FILES),
    ROLE_GET_ALL_PRESENTATION(Roles.ROLE_GET_ALL_PRESENTATION),
    ROLE_GET_PRESENTATION(Roles.ROLE_GET_PRESENTATION),
    ROLE_CREATED_PRESENTATION(Roles.ROLE_CREATED_PRESENTATION),
    ROLE_UPDATED_PRESENTATION(Roles.ROLE_UPDATED_PRESENTATION),
    ROLE_DELETE_PRESENTATION(Roles.ROLE_DELETE_PRESENTATION),
    ROLE_GET_ALL_TVS(Roles.ROLE_GET_ALL_TVS);

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public interface Roles {
        String ROLE_READ_ROLE = "READ_ROLE";
        String ROLE_CREATE_USER = "ROLE_CREATE_USER";
        String ROLE_ADD_USER_GROUPS_ROLES = "ADD_USER_GROUPS_ROLES";
        String ROLE_REMOVE_USER_GROUPS_ROLES = "REMOVE_USER_GROUPS_ROLES";
        String ROLE_GET_USER_ROLES = "GET_USER_ROLES";
        String ROLE_ADD_USER_ROLES = "ADD_USER_ROLES";
        String ROLE_REMOVE_USER_ROLES = "REMOVE_USER_ROLES";
        String ROLE_GET_GROUPS_ROLES = "GET_GROUPS_ROLES";
        String ROLE_GET_GROUP_ROLES_DETAILS = "GET_GROUP_ROLES_DETAILS";
        String ROLE_CREATE_GROUP_ROLES = "CREATE_GROUP_ROLES";
        String ROLE_UPDATE_GROUP_ROLES = "UPDATE_GROUP_ROLES";
        String ROLE_REMOVE_GROUP_ROLES = "REMOVE_GROUP_ROLES";
        String ROLE_ADD_GROUP_ROLES_ROLES = "ADD_GROUP_ROLES_ROLES";
        String ROLE_REMOVE_GROUP_ROLES_ROLES = "REMOVE_GROUP_ROLES_ROLES";
        String ROLE_GET_AVAILABLE_PERMISSIONS = "ROLE_GET_AVAILABLE_PERMISSIONS";
        String ROLE_CREATE_LICENSES = "ROLE_CREATE_LICENSES";
        String ROLE_GET_LICENSES = "ROLE_GET_LICENSES";
        String ROLE_GET_LICENSE_DETAILS = "ROLE_GET_LICENSE_DETAILS";
        String ROLE_DOWNLOAD_LICENSE = "ROLE_DOWNLOAD_LICENSE";
        String ROLE_UPDATED_PASSWORD = "ROLE_UPDATED_PASSWORD";
        String ROLE_GET_USERS = "ROLE_GET_USERS";
        String ROLE_CREATED_FILE = "ROLE_CREATED_FILE";
        String ROLE_GET_FILE = "ROLE_GET_FILE";
        String ROLE_DELETED_FILE = "ROLE_DELETED_FILE";
        String ROLE_GET_ALL_FILES = "ROLE_GET_ALL_FILES";
        String ROLE_GET_ALL_PRESENTATION = "ROLE_GET_ALL_PRESENTATION";
        String ROLE_GET_PRESENTATION = "ROLE_GET_PRESENTATION";
        String ROLE_CREATED_PRESENTATION = "ROLE_CREATED_PRESENTATION";
        String ROLE_UPDATED_PRESENTATION = "ROLE_UPDATED_PRESENTATION";
        String ROLE_DELETE_PRESENTATION = "ROLE_DELETE_PRESENTATION";
        String ROLE_GET_ALL_TVS = "ROLE_GET_ALL_TVS";
    }
}
