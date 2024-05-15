package br.com.base.authentication.api.controllers.user.v1.models.enums;

import lombok.Getter;

import java.util.UUID;

@Getter
public enum ProfileType {
    ADMINISTRATOR(UUID.fromString("d7f29fa4-bd05-43d4-9472-7e1cbac522fa"), "Administrador"),
    USER(UUID.fromString("6e62666e-ac63-4f3f-b9fc-9252365cf5bf"), "Usuario"),
    TV(UUID.fromString("d2cc6ae2-6473-42be-9b01-616f1e4a4a4d"), "TV");

    private final UUID groupRolesId;
    private final String name;

    ProfileType(UUID groupRolesId, String name) {
        this.groupRolesId = groupRolesId;
        this.name = name;
    }

    public static ProfileType findKeyByPartialValue(String partialValue) {
        for (ProfileType enumItem : ProfileType.values()) {
            if (enumItem.name.toLowerCase().contains(partialValue.toLowerCase())) {
                return enumItem;
            }
        }
        return null;
    }
}
