package com.repository;

import com.entity.Favorite;

import com.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    public Favorite findByIdUserAndIdLocation(Long idUser, Long idLocation);
    public Object deleteByIdUserAndIdLocation(Long idUser, Long idLocation);



}
