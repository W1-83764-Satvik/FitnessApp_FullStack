package com.fitness.userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponce {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
