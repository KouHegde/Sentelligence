package com.example.SentInteligence.HttpService.Implementation;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class IHttpWrapper {
    private static final Logger logger = Logger.getLogger(IHttpWrapper.class.getName());
    private final HttpClient httpClient;

    // Constructor to initialize the HttpClient
    public IHttpWrapper() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // Wrapper for HTTP GET
    public HttpResponse<String> get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            logRequest(request);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logResponse(response);

            return response;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "HTTP GET request failed", e);
            throw new RuntimeException(e);
        }
    }

    // Wrapper for HTTP POST
    public HttpResponse<String> post(String url, String body) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            logRequest(request);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logResponse(response);

            return response;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "HTTP POST request failed", e);
            throw new RuntimeException(e);
        }
    }

    // Wrapper for HTTP PUT
    public HttpResponse<String> put(String url, String body) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            logRequest(request);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logResponse(response);

            return response;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "HTTP PUT request failed", e);
            throw new RuntimeException(e);
        }
    }

    // Logging the request
    private void logRequest(HttpRequest request) {
        logger.info("HTTP Request: ");
        logger.info("URI: " + request.uri());
        logger.info("Method: " + request.method());
        if (request.bodyPublisher().isPresent()) {
            logger.info("Body: " + request.bodyPublisher().get().toString());
        }
    }

    // Logging the response
    private void logResponse(HttpResponse<String> response) {
        logger.info("HTTP Response: ");
        logger.info("Status Code: " + response.statusCode());
        logger.info("Body: " + response.body());
    }
}
