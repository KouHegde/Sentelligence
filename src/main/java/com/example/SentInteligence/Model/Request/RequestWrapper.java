package com.example.SentInteligence.Model.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class RequestWrapper <REQ>{
    private String convId;         // Conversation ID
    private String orgId;          // Organization ID
    private REQ body;                // Generic request body
    private Map<String, String> headers; // Additional headers (optional)
    private Map<String, String> requestParams; // Request query parameters (optional)
    private long timestamp;
}
