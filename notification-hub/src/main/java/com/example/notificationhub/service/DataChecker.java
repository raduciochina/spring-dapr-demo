package com.example.notificationhub.service;

import com.example.notificationhub.model.Content;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataChecker {

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private ObjectMapper objectMapper;
    private final String url = "http://localhost:8080/api/content";

    public DataChecker() {
    }

    public void checkData() throws JsonProcessingException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        String responseBody = null;
        List<Content> contentList = new ArrayList<>();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            responseBody = response.body();

            System.out.println("Response Body: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (responseBody != null) {
            contentList = objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Content.class));
            System.out.println(contentList);
            for (Content content:
                 contentList) {
                Duration duration = Duration.between(LocalDateTime.now(), content.getDeadline());
                long minutes = duration.toMinutes();
                System.out.println(minutes);
                if(minutes < 60 && minutes >= 0 && !content.getIsNotified()){
                    sendEmail("Deadline approaching", "The deadline for " + content.getTitle() + " is approaching!" +
                            " Description: " + content.getDescription() + " Deadline: " + content.getDeadline() + "");
                    content.setIsNotified(true);
                    String requestBody = objectMapper.writeValueAsString(content);
                    HttpRequest putHttpRequest = HttpRequest.newBuilder()
                            .uri(URI.create(url + "/" + content.getId()))
                            .header("Content-Type", "application/json")
                            .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();
                    try {
                        HttpResponse<String> response = httpClient.send(putHttpRequest, HttpResponse.BodyHandlers.ofString());
                        int statusCode = response.statusCode();
                        String putResponseBody = response.body();
                        if (statusCode >= 200 && statusCode < 300) {
                            System.out.println("Response body: " + putResponseBody);
                        } else {
                            System.out.println("Request failed with status code: " + statusCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(minutes < 0 && !content.getIsNotified()){
                    sendEmail("Deadline passed", "The deadline for " + content.getTitle() + " has passed!");
                    content.setIsNotified(true);
                    String requestBody = objectMapper.writeValueAsString(content);
                    HttpRequest putHttpRequest = HttpRequest.newBuilder()
                            .uri(URI.create(url + "/" + content.getId()))
                            .header("Content-Type", "application/json")
                            .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();
                    try {
                        HttpResponse<String> response = httpClient.send(putHttpRequest, HttpResponse.BodyHandlers.ofString());
                        int statusCode = response.statusCode();
                        String putResponseBody = response.body();
                        if (statusCode >= 200 && statusCode < 300) {
                            System.out.println("Response body: " + putResponseBody);
                        } else {
                            System.out.println("Request failed with status code: " + statusCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

//    @EventListener(ApplicationReadyEvent.class)
    private void sendEmail(String subject, String text) {
        emailSender.sendEmail("ciochina7@gmail.com", subject, text);
    }
}
