package org.example.utils;

import java.util.ResourceBundle;

public class PropertyUtils {
    private PropertyUtils() {}

    public static ResourceBundle getCommonProperties() {
        return ResourceBundle.getBundle("common");
    }

    public static ResourceBundle getSelenideProperties() {
        return ResourceBundle.getBundle("selenide");
    }

    public static ResourceBundle getCredentialsProperties() {
        // I understand that it's not good approach to store credentials in properties.
        // But for this test task I assume it's acceptable.
        // In a production system it's more proper to store credentials somewhere in a
        // secured storage (like AWS Secrets Manager) and here in properties file store only
        // reference for them.
        return ResourceBundle.getBundle("credentials");
    }
}
