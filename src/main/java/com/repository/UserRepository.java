package com.repository;

import com.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository  extends CrudRepository<User, Long> {

    @Query( value = "SELECT DISTINCT  user.* FROM USER  INNER JOIN  evaluation ON  user.ID = evaluation.id_user  " +
            "WHERE evaluation.id_location in (select evl.id_location FROM  evaluation AS evl WHERE evl.id_user = ?1 )",  nativeQuery = true)
    public List<User>  getAllUserHaveSameRating(Long idUser);
}
