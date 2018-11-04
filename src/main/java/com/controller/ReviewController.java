package com.controller;

import com.dto.APIResponseDTO;
import com.dto.ReviewDTO;
import com.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping(value = "/app/review-location")
    public APIResponseDTO reviewLocation(@RequestBody ReviewDTO reviewDTO){
        return new APIResponseDTO(200, "Review Successfull!", reviewService.reviewLocation(reviewDTO));
    }

}
