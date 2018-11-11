package com.controller;

import com.dto.APIResponseDTO;
import com.dto.ReviewDTO;
import com.repository.CommentRepository;
import com.service.CommentService;
import com.service.EvaluationService;
import com.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    CommentService commentService;

    @Autowired
    EvaluationService evaluationService;

    @PostMapping(value = "/app/review-location")
    public APIResponseDTO reviewLocation(@RequestBody ReviewDTO reviewDTO){
        return new APIResponseDTO(200, "Review Successfull!", reviewService.reviewLocation(reviewDTO));
    }

    @PostMapping(value = "/app/api/list-top-comment-of-location")
    public APIResponseDTO getTopCommentOfLocation(@RequestParam("idLocation") Long idLocation){
        return new APIResponseDTO(200,"Ok", commentService.getTop10CommentOfLocation(idLocation));
    }

    @GetMapping(value = "/app/review/{idLocation}/{crrPage}")
    public APIResponseDTO getReviewPagination(@PathVariable Long idLocation, @PathVariable int crrPage){
        return new APIResponseDTO(200,"Ok", evaluationService.findAppReviewDTOPagination(idLocation,crrPage));
    }

    @DeleteMapping(value ="/delete/review/{id}")
    public APIResponseDTO deleteReview(@PathVariable Long id){
        evaluationService.deleteEvaluation(id);
        return new APIResponseDTO(200,  "OK", null);
    }

    @GetMapping(value = "/reviews")
    public APIResponseDTO getAllReviews(){
        return new APIResponseDTO(200,"Ok", evaluationService.findAllEvaluation());
    }

//    @DeleteMapping(value = "/")
}
