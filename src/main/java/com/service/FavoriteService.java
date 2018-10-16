package com.service;

import com.entity.Favorite;
import com.entity.PlaceCategory;
import com.repository.FavoriteRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    public List<Favorite> findAllFavorite(){
        return (List<Favorite>) favoriteRepository.findAll();
    }

    public Optional<Favorite> findById(Long id){
        return favoriteRepository.findById(id);
    }

    public  void createFavorite(Favorite favorite){
        favoriteRepository.save(favorite);
    }

    public void updateFavorite(Favorite favorite){
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long id){
        favoriteRepository.deleteById(id);
    }
}
