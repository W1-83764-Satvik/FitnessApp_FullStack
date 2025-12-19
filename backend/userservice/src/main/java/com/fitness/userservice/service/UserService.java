package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponce;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    public UserResponce register(RegisterRequest registerRequest){

        if(userRepo.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("Email \""+ registerRequest.getEmail() + "\" already exist!!");
        }

        User user = User.builder().email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .build();

        User savedUser = userRepo.save(user);

        UserResponce responce = UserResponce.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .createdOn(savedUser.getCreatedOn())
                .updatedOn(savedUser.getUpdatedOn())
                .build();

        return responce;
    }
}
