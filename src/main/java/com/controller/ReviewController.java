package com.controller;

import com.dto.APIResponseDTO;
import com.dto.ReviewDTO;
import com.repository.CommentRepository;
import com.service.CommentService;
import com.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/app/review-location")
    public APIResponseDTO reviewLocation(@RequestBody ReviewDTO reviewDTO){
        return new APIResponseDTO(200, "Review Successfull!", reviewService.reviewLocation(reviewDTO));
    }

    @PostMapping(value = "/app/api/list-top-comment-of-location")
    public APIResponseDTO getTopCommentOfLocation(@RequestParam("idLocation") Long idLocation){
        return new APIResponseDTO(200,"Ok", commentService.getTop10CommentOfLocation(idLocation));
    }
}
