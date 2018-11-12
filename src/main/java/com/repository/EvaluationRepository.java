package com.repository;

import com.entity.Evaluation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    public List<Evaluation> findAllByIdUser(Long idUser);

    public Evaluation findEvaluationByIdUserAndIdLocation(Long idUser, Long idLocation);

    public void deleteByIdLocation(Long idLocation);

    @Query(value = "SELECT evaluation.* FROM evaluation WHERE evaluation.id_user = ?2 AND evaluation.id_location IN (SELECT evl.id_location FROM evaluation AS evl Where evl.id_user = ?1 );" , nativeQuery = true)
    public List<Evaluation> findAllRatingWithTheSameLocationOfTwoUser(Long idUserRecommend, Long idUser2);

    public long countAllByIdLocation(Long idLocation);

    @Query(value = "select SUM(score) from evaluation WHERE id_location = ?1", nativeQuery = true)
    public Optional<BigDecimal> sumAllByIdLocation(Long idLocation);

    public Page<Evaluation> findAllByIdLocation(Long idLocation, Pageable pageable);
}
