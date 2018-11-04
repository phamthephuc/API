package com.service;

import com.dto.ReviewDTO;
import com.entity.Comment;
import com.entity.Evaluation;
import com.repository.CommentRepository;
import com.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class ReviewService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    public ReviewDTO reviewLocation(@RequestBody ReviewDTO reviewDTO){
        Evaluation evaluation = new Evaluation();
        evaluation.setIdUser(reviewDTO.getUserId());
        evaluation.setScore(reviewDTO.getNumberRating());
        evaluation.setIdLocation(reviewDTO.getLocationId());
        Evaluation evaluationAdded = evaluationRepository.save(evaluation);

        Comment comment = new Comment();
        comment.setCommentedDate(new Date());
        comment.setContent(reviewDTO.getCommentContent());
        comment.setIdUser(reviewDTO.getUserId());
        comment.setIdLocation(reviewDTO.getLocationId());
        Comment commentAdded = commentRepository.save(comment);

        return null;
    }

}
