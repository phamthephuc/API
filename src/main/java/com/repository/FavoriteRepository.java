package com.repository;

import com.entity.Favorite;

import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    public Favorite findByIdUserAndIdLocation(Long idUser, Long idLocation);

}
