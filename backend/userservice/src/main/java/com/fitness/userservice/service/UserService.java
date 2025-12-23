package com.fitness.userservice.service;

import com.fitness.userservice.dto.request.RegisterRequest;
import com.fitness.userservice.dto.response.UserResponse;
import com.fitness.userservice.exceptions.customException.UserAlreadyExistException;
import com.fitness.userservice.exceptions.customException.UserNotFoundException;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;


    public UserResponse register(RegisterRequest registerRequest){

        if(userRepo.existsByEmail(registerRequest.getEmail())){
            throw new UserAlreadyExistException("Email \""+ registerRequest.getEmail() + "\" already exist!!");
        }

        User user = User.builder().email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .build();

        User savedUser = userRepo.save(user);

        UserResponse response = UserResponse.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .createdOn(savedUser.getCreatedOn())
                .updatedOn(savedUser.getUpdatedOn())
                .build();

        return response;
    }

    public UserResponse getUserProfile(String userId) {
        User savedUser = userRepo.findById(userId).orElseThrow( ()-> new UserNotFoundException("User not found with id "+ userId));

        return UserResponse.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .createdOn(savedUser.getCreatedOn())
                .updatedOn(savedUser.getUpdatedOn())
                .build();

    }
}
