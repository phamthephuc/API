package com.service;

import com.dto.CategoryResponseDTO;
import com.dto.LocationDTO;
import com.dto.LocationProfileDTO;
import com.dto.TypeResponseDTO;
import com.entity.Location;
import com.entity.PlaceCategory;
import com.entity.PlaceType;
import com.repository.PlaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceTypeService {

    @Autowired
    PlaceTypeRepository placeTypeRepository;

    @Autowired
    LocationService locationService;

    @Autowired
    PlaceTypeService placeTypeService;

    @Autowired
    PlaceCategoryService placeCategoryService;

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

    public TypeResponseDTO findTypeResponseDTOByIdType(Long idType) {
        LocationProfileDTO locationProfileDTOLastest = locationService.findLastestLocationByIdType(idType);
        ArrayList<CategoryResponseDTO> listCRDTO = placeCategoryService.findAllCategoryDetailOfOneType(idType);
        return new TypeResponseDTO(locationProfileDTOLastest,listCRDTO);
    }

}