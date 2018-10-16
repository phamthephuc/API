package com.repository;

import com.entity.Favorite;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

}
