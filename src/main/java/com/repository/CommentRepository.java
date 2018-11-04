package com.repository;

import com.entity.Comment;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    public List<Comment> findTop10ByIdLocation(Long idLocation);

}
