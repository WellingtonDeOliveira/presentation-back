package br.com.base.shared.models.enums;

public enum AllowedExtension {
    JPG(".jpg"),
    JPEG(".jpeg"),
    PNG(".png"),
    mp4(".mp4");

    private final String extension;

    AllowedExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        return lastDotIndex != -1 ? filename.substring(lastDotIndex + 1) : null;
    }

    public static boolean isAllowedExtension(String extension) {
        for (AllowedExtension allowedExtension : AllowedExtension.values()) {
            if (allowedExtension.getExtension().equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}
