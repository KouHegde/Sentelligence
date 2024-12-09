package com.example.SentInteligence.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.logging.Logger;


public final class JsonUtils {

    private static final ObjectMapper objectMapper;

    static {
        // Initialize the ObjectMapper
        objectMapper = new ObjectMapper();

        // Configure ObjectMapper to ignore unknown properties
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Enabling the handling of Java 8 Date and Time API types (LocalDate, LocalDateTime, etc.)
        objectMapper.registerModule(new JavaTimeModule());

        // Enable timestamps as ISO-8601 format (standard date format)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * Convert an object to JSON string.
     *
     * @param obj The object to be converted.
     * @return The JSON string representation of the object.
     */
    public static String toJson(Object obj) throws JsonProcessingException {
            // Perform the conversion and return
            return objectMapper.writeValueAsString(obj);

    }

    /**
     * Convert a JSON string to an object of the specified class.
     *
     * @param json The JSON string to be deserialized.
     * @param clazz The class type to convert to.
     * @param <T> The type of the object.
     * @return The deserialized object.
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {

        return objectMapper.readValue(json, clazz);

    }

    /**
     * Get a property from a JSON string by key.
     *
     * @param json The JSON string.
     * @param key The property key.
     * @return The value of the property, or null if not found.
     */
    public static String getProperty(String json, String key) throws JsonProcessingException {
        JsonNode jsonNode = toJsonNode(json);
        if (jsonNode != null && jsonNode.has(key)) {
            return jsonNode.get(key).asText();
        }
        return null;
    }

    /**
     * Convert a JSON string to a JsonNode, which is useful for extracting properties dynamically.
     *
     * @param json The JSON string to be deserialized.
     * @return The JsonNode representing the JSON.
     */
    public static JsonNode toJsonNode(String json) throws JsonProcessingException {

            return objectMapper.readTree(json);

    }


}
