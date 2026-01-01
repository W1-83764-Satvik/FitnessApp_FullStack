package com.fitness.activityservice.service;

import jakarta.ws.rs.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    public final WebClient userServiceWebClient;

    public boolean validateUser(String userId){
        try{
            return userServiceWebClient.get()
                    .uri("/api/v1/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch(WebClientResponseException e) {
            e.printStackTrace();
        }
        return false;

    }
    
}
