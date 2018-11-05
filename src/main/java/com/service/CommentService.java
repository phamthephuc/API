package com.service;

import com.entity.Comment;
import com.entity.PlaceCategory;
import com.repository.CommentRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findAllComment(){
        return (List<Comment>) commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }

    public  void createComment(Comment comment){
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment){
        commentRepository.save(comment);
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public List<Comment> getTop10CommentOfLocation(Long idLocation){
        return  commentRepository.findTop10ByIdLocation(idLocation);
    }
}
