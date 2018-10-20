package com.repository;

import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceTypeRepository extends CrudRepository<PlaceType, Long> {
    public List<PlaceType>  findAllByOrderByIdDesc();

}
