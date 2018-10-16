package com.service;

import com.dto.LocationDTO;
import com.entity.Location;
import com.entity.PlaceCategory;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    DurationRepository durationRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PlaceCategoryRepository placeCategoryRepository;

    @Autowired


    public List<LocationDTO> findAllLocation(){
        ArrayList<LocationDTO> listLocationDTO = new ArrayList<>();
        ArrayList<Location> listLocations = (ArrayList<Location>) locationRepository.findAll();
        return  listLocationDTO;
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
