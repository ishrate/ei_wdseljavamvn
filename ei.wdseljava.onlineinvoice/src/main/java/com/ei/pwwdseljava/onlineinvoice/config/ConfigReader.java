
package com.ei.pwwdseljava.onlineinvoice.config;

import com.ei.pwwdseljava.onlineinvoice.utils.EncryptionUtil;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        String env = System.getProperty("env", "dev"); // default to 'dev'
        String fileName = "config-" + env + ".properties";

        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.err.println("Config file " + fileName + " not found. Falling back to config.properties.");
                try (InputStream fallback = ConfigReader.class.getClassLoader()
                        .getResourceAsStream("config.properties")) {
                    if (fallback == null) {
                        throw new RuntimeException("No fallback config.properties found.");
                    }
                    properties.load(fallback);
                    System.out.println("Loaded fallback config.properties");
                }
            } else {
                properties.load(input);
                System.out.println("Loaded config file: " + fileName);
                System.out.println("Successfully found and loaded config file: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key, "false"));
    }

    public static int getInt(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing integer config for key: " + key);
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer for key: " + key + ", value: " + value);
        }
    }

    public static List<String> getList(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(value.split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    public static void reload() {
        properties.clear();
        loadProperties();
    }

    public static String getReportPath() {
        String baseReportPath = "test-output/reports";
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return baseReportPath + "/report_" + timestamp;
    }

    // --- New method for password decryption support ---
    private static final String ENCRYPTED_PREFIX = "ENC(";
    private static final String ENCRYPTED_SUFFIX = ")";

    private static String getSecretKey() {
        String key = System.getenv("ENCRYPTION_SECRET_KEY");
        if (key == null || key.length() != 16) {
            throw new RuntimeException("ENCRYPTION_SECRET_KEY must be set and 16 characters long for AES");
        }
        return key;
    }

    public static String getPassword(String propertyKey) {
        String value = get(propertyKey);
        if (value != null && value.startsWith(ENCRYPTED_PREFIX) && value.endsWith(ENCRYPTED_SUFFIX)) {
            String encrypted = value.substring(ENCRYPTED_PREFIX.length(), value.length() - ENCRYPTED_SUFFIX.length());
            try {
                return EncryptionUtil.decrypt(encrypted, getSecretKey());
            } catch (Exception e) {
                throw new RuntimeException("Failed to decrypt password for key: " + propertyKey, e);
            }
        }
        return value;
    }
}
