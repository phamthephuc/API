package com.repository;

import com.entity.Location;
import com.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query(value = "Select * from location inner JOIN evaluation on evaluation.id_location = location.id " +
            "where evaluation.id_user = ?1", nativeQuery = true)
    public List<Location> getAllLocationByUser(Long id);

    public List<Location> findByIdPlaceCategory(Long id);

    @Query(value = "SELECT location.* FROM location INNER JOIN evaluation ON location.id = evaluation.id_location WHERE evaluation.id_user = ?2 AND evaluation.id_location NOT IN (SELECT evl.id_location FROM evaluation AS evl Where evl.id_user = ?1 ) ORDER BY evaluation.score DESC LIMIT 0,10;" , nativeQuery = true)
    public List<Location> getLocationRecommend(Long idUserRecommend, Long idUser2);

    @Query(value = "SELECT location.* FROM location INNER JOIN evaluation ON location.id = evaluation.id_location ORDER BY evaluation.score DESC LIMIT 0,10;" , nativeQuery = true)
    public List<Location> getLocationRecommendWithHeightScore();
}
