package br.com.base.shared.models.enums;

public enum ProblemType {
    INVALID_DATA("/invalid-data", "ProblemType.Title.InvalidData"),
    ACCESS_DENIED("/access-denied", "ProblemType.Title.AccessDenied"),
    RESOURCE_NOT_FOUND("/resource-not-found", "ProblemType.Title.ResourceNotFound"),
    INVALID_PARAMETER("/invalid-parameter", "ProblemType.Title.InvalidParameter"),
    SYSTEM_ERROR("/system-error", "ProblemType.Title.SystemError"),
    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "ProblemType.Title.IncomprehensibleMessage"),
    ENTITY_IN_USE("/entity-in-use", "ProblemType.Title.EntityInUse"),
    BUSINESS_ERROR("/business-error", "ProblemType.Title.BusinessError");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = String.format("%s%s", "http://localhost", path);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
