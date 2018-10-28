package com.service;

import com.dto.CategoryResponseDTO;
import com.dto.LocationProfileDTO;
<<<<<<< HEAD
import com.dto.LocationProfileForTypeDTO;
=======
>>>>>>> edb20ba80bd3d383a3f1c9026b89bec78532cc7f
import com.entity.PlaceCategory;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceCategoryService {

    @Autowired
    PlaceCategoryRepository placeCategoryRepository;

    @Autowired
    LocationService locationService;

    public List<PlaceCategory> findAllPlaceCategory(){
        return (List<PlaceCategory>) placeCategoryRepository.findAll();
    }

    public List<PlaceCategory> findAllPlaceCategoryOfOneType(Long id){
        return placeCategoryRepository.findByIdPlaceType(id);
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

    public ArrayList<CategoryResponseDTO> findAllCategoryDetailOfOneType(Long id) {
        ArrayList<CategoryResponseDTO> listCTDTO = new ArrayList<>();
        List<PlaceCategory> listPlaceCategoty = findAllPlaceCategoryOfOneType(id);
        for(PlaceCategory placeCategory : listPlaceCategoty) {
<<<<<<< HEAD
            List<LocationProfileForTypeDTO> listLocationOfOneCategory = locationService.findTop10LocationProfileByCategoryId(placeCategory.getId());
=======
            List<LocationProfileDTO> listLocationOfOneCategory = locationService.findTop10LocationProfileByCategoryId(placeCategory.getId());
>>>>>>> edb20ba80bd3d383a3f1c9026b89bec78532cc7f
            listCTDTO.add(new CategoryResponseDTO(placeCategory.getId(),placeCategory.getName(),listLocationOfOneCategory));
        }
        return listCTDTO;
    }
}
