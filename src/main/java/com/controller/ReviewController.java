package com.controller;

import com.dto.APIResponseDTO;
import com.dto.ReviewDTO;
import com.entity.Evaluation;
import com.entity.InforUsers;
import com.entity.Traveler;
import com.repository.CommentRepository;
import com.service.CommentService;
import com.service.EvaluationService;
import com.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class ReviewController {



    @Autowired
    CommentService commentService;

    @Autowired
    EvaluationService evaluationService;

    @PostMapping(value = "/api/review-location")
    public APIResponseDTO reviewLocation(@RequestBody ReviewDTO reviewDTO){
        return new APIResponseDTO(200, "Review Successfull!", evaluationService.reviewLocation(reviewDTO));
    }

    @PostMapping(value = "/api/app/review-location")
    public APIResponseDTO reviewLocationApp(HttpServletRequest request, @RequestBody ReviewDTO reviewDTO){
        return new APIResponseDTO(200, "Review Successfull!", evaluationService.reviewLocationForApp(request, reviewDTO));
    }

    @PutMapping(value = "/api/app/edit-review-location")
    public APIResponseDTO editReviewLocation(HttpServletRequest request, @RequestBody ReviewDTO reviewDTO){
        if (evaluationService.editReviewLocation(request, reviewDTO)){
            return new APIResponseDTO(200, "Edited", null);
        } else {
            return new APIResponseDTO(500, "Review isn't exist", null);
        }
    }

    //get review cua user cua 1 dia diem
    @GetMapping("/api/app/location-review/{idLocation}")
    public APIResponseDTO getReviewLocation(HttpServletRequest request, @PathVariable Long idLocation){
        Evaluation evaluation = evaluationService.getReviewLocation(request,idLocation);
        if (evaluation != null){
            return new APIResponseDTO(200,"Ok", evaluation);
        } else {
            return new APIResponseDTO(500,"Review isn't exist", null);

        }
    }



    @PostMapping(value = "/app/api/list-top-comment-of-location")
    public APIResponseDTO getTopCommentOfLocation(@RequestParam("idLocation") Long idLocation){
        return new APIResponseDTO(200,"Ok", commentService.getTop10CommentOfLocation(idLocation));
    }

    @GetMapping(value = "/app/review/{idLocation}/{crrPage}")
    public APIResponseDTO getReviewPagination(@PathVariable Long idLocation, @PathVariable int crrPage){

        return new APIResponseDTO(200,"Ok", evaluationService.findAppReviewDTOPagination(idLocation,crrPage));
    }

    @GetMapping(value = "/api/web/review/{idLocation}/{crrPage}")
    public APIResponseDTO getReviewWebPagination(@PathVariable Long idLocation, @PathVariable int crrPage){

        return new APIResponseDTO(200,"Ok", evaluationService.findWebReviewDTOPagination(idLocation,crrPage));
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
