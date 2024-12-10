package com.example.SentInteligence.Model.Request;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWrapper <REQ>{
    private String convId;         // Conversation ID
    private String orgId;          // Organization ID
    private REQ body;                // Generic request body
    private Map<String, String> headers; // Additional headers (optional)
    private Map<String, String> requestParams; // Request query parameters (optional)
    private long timestamp;
    private int offset;
    private int limit;

    public RequestWrapper(String convId,String orgId, REQ body,Map<String, String> requestParams){
        this.convId = convId;
        this.orgId = orgId;
        this.timestamp = System.currentTimeMillis();
        this.setBody(body);
        this.headers = null;
        this.requestParams = requestParams;
    }
    public RequestWrapper(String convId,String orgId, REQ body,Map<String, String> requestParams,int offset,int limit){
        this.convId = convId;
        this.orgId = orgId;
        this.timestamp = System.currentTimeMillis();
        this.setBody(body);
        this.headers = null;
        this.requestParams = requestParams;
        this.offset = offset;
        this.limit = limit;
    }

}
