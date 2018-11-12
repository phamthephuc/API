package com.service;

import com.dto.ReviewDTO;
import com.entity.Comment;
import com.entity.Evaluation;
import com.repository.CommentRepository;
import com.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EvaluationRepository evaluationRepository;




}
