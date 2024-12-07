package com.example.SentInteligence.Model.Response;

import lombok.*;

import java.util.Map;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<RES> {
    private int statusCode;         // HTTP status code of the response (e.g., 200, 404)
    private String statusMessage;    // Descriptive message corresponding to the status code (e.g., "OK")
    private RES body;                 // Generic response body (the actual data)
    private Map<String, String> headers;  // Response headers (optional)
    private Map<String, String> responseParams; // Additional response parameters (optional)
    private Map<String, String> errors; // Any error details (optional)
    private long timestamp;

}
