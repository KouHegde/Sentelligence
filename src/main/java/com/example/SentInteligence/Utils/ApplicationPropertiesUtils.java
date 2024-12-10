package com.example.SentInteligence.Utils;

import com.example.SentInteligence.Model.Response.ConvosResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    public ConvosResponse getOrgConvIds(String orgConvIdJson) throws JsonProcessingException {
        if (orgConvIdJson != null && !orgConvIdJson.isEmpty()) {
            return JsonUtils.fromJson(orgConvIdJson, ConvosResponse.class);
        }
        return null;
    }
}

