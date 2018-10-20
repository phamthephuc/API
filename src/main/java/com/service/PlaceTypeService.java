package com.service;

import com.entity.PlaceType;
import com.repository.PlaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceTypeService {

    @Autowired
    PlaceTypeRepository placeTypeRepository;

    public List<PlaceType> findAll(){
        return (List<PlaceType>) placeTypeRepository.findAllByOrderByIdDesc();
    }

    public void save(PlaceType placeType){
        placeTypeRepository.save(placeType);
    }

    public Optional<PlaceType> findById(Long id){
        return   placeTypeRepository.findById(id);
    }
    public  void  deleteById(Long id){
        placeTypeRepository.deleteById(id);
    }

}