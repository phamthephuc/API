package com.repository;

import com.entity.Evaluation;
import com.entity.PlaceType;
import com.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {



}
