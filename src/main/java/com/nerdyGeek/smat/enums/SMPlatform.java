package com.nerdyGeek.smat.enums;

public enum SMPlatform {
    instagram("INSTAGRAM"), whatsapp_cloud("WHATSAPP_CLOUD");

    private final String value;

    // Constructor
    SMPlatform(String value) {
        this.value = value;
    }

    // Getter method
    public String getValue() {
        return value;
    }

    // Static method to get enum from string
    public static SMPlatform fromString(String text) {
        for (SMPlatform platform : SMPlatform.values()) {
            if (platform.value.equalsIgnoreCase(text)) {
                return platform;
            }
        }
        throw new IllegalArgumentException("Invalid platform: " + text);
    }
}