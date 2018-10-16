package com.service;

import com.entity.Location;
import com.entity.PlaceCategory;
import com.repository.LocationRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> findAllLocation(){
        return (List<Location>) locationRepository.findAll();
    }

    public Optional<Location> findById(Long id){
        return locationRepository.findById(id);
    }

    public  void createLocation(Location location ){
        locationRepository.save(location);
    }

    public void updateLocation(Location location){
        locationRepository.save(location);
    }

    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }
}
