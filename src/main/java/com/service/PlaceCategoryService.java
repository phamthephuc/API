package com.service;

import com.entity.PlaceCategory;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceCategoryService {

    @Autowired
    PlaceCategoryRepository placeCategoryRepository;

    public List<PlaceCategory> findAllPlaceCategory(){
        return (List<PlaceCategory>) placeCategoryRepository.findAll();
    }

    public Optional<PlaceCategory> findById(Long id){
        return placeCategoryRepository.findById(id);
    }

    public  void createPlaceCategory(PlaceCategory placeCategory){
        placeCategoryRepository.save(placeCategory);
    }

    public void updatePlaceCategory(PlaceCategory placeCategory){
        placeCategoryRepository.save(placeCategory);
    }

    public void deletePlaceCategory(Long id){
        placeCategoryRepository.deleteById(id);
    }
}
