package com.example.SentInteligence.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class ApplicationPropertiesUtils {

    private final Map<String, String> propertiesMap;

    @Autowired
    public ApplicationPropertiesUtils(@Qualifier("applicationProperties") Map<String, String> applicationProperties) {
        this.propertiesMap = applicationProperties;
    }

    public String getPropertyValue(String key) {
        return propertiesMap.get(key);
    }

    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(propertiesMap.get(key));
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(propertiesMap.get(key));
    }
}