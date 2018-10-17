package com.repository;

import com.entity.PlaceCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceCategoryRepository extends CrudRepository<PlaceCategory, Long> {
    public List<PlaceCategory> findByIdPlaceType(Long id);

}
