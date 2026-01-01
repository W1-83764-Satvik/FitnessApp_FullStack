package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.request.ActivityRequest;
import com.fitness.activityservice.dto.response.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    public ActivityResponse addActivity(ActivityRequest activityRequest) {

        boolean isUserValid = userValidationService.validateUser(activityRequest.getUserId());

        if(!isUserValid){
            throw new RuntimeException("Invalid user id" + activityRequest.getUserId());
        }

         Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .endTime(activityRequest.getEndTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();
         Activity savedActivity = activityRepository.save(activity);

        return ActivityResponse.builder()
                .userId(savedActivity.getUserId())
                .type(savedActivity.getType())
                .duration(savedActivity.getDuration())
                .caloriesBurned(savedActivity.getCaloriesBurned())
                .startTime(savedActivity.getStartTime())
                .endTime(savedActivity.getEndTime())
                .additionalMetrics(savedActivity.getAdditionalMetrics())
                .createdAt(savedActivity.getCreatedAt())
                .updatedAt(savedActivity.getUpdatedAt())
                .build();
    }
}
