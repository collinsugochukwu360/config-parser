package src.week2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {

    private final Map<String, String> configValues = new HashMap<>();

    public ConfigParser(String fileName) {

        String currentSection = "";

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                // Section
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection =
                            line.substring(1, line.length() - 1);

                    continue;
                }

                // Key=value
                if (line.contains("=")) {

                    String[] parts = line.split("=", 2);

                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    String fullKey;

                    if (currentSection.isEmpty()) {
                        fullKey = key;
                    } else {
                        fullKey = currentSection + "." + key;
                    }

                    // First occurrence wins
                    if (!configValues.containsKey(fullKey)) {
                        configValues.put(fullKey, value);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return configValues.get(key);
    }
}
