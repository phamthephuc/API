package com.repository;

import com.entity.Comment;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
