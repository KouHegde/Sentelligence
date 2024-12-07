package com.example.SentInteligence.HttpService;
import java.net.http.HttpResponse;

public interface HttpService {
    // HTTP GET method
    HttpResponse<String> get(String url);

    // HTTP POST method
    HttpResponse<String> post(String url, String body);

    // HTTP PUT method
    HttpResponse<String> put(String url, String body);
}
