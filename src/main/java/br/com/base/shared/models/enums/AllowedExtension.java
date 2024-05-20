package br.com.base.shared.models.enums;

import br.com.base.shared.exceptions.BusinessException;
import lombok.Getter;

@Getter
public enum AllowedExtension {
    JPG(".jpg", "imagem"),
    JPEG(".jpeg", "imagem"),
    PNG(".png", "imagem"),
    mp4(".mp4", "video");

    private final String extension;
    private final String type;

    AllowedExtension(String extension, String type) {
        this.extension = extension;
        this.type = type;
    }

    public static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        return lastDotIndex != -1 ? filename.substring(lastDotIndex) : null;
    }

    public static boolean isAllowedExtension(String extension) {
        for (AllowedExtension allowedExtension : AllowedExtension.values()) {
            if (allowedExtension.getExtension().equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public static String getTypeByExtension(String extension) {
        for (AllowedExtension allowedExtension : AllowedExtension.values()) {
            if (allowedExtension.getExtension().equalsIgnoreCase(extension)) {
                return allowedExtension.getType();
            }
        }
        return null;
    }
}
