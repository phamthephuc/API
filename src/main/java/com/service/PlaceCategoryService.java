package com.service;

import com.dto.CategoryResponseDTO;
import com.dto.LocationProfileDTO;
import com.dto.LocationProfileForTypeDTO;
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

    public CategoryResponseDTO findCategoryInfoForApp(Long id) {
        PlaceCategory placeCategory = findById(id).orElse(new PlaceCategory());
        List<LocationProfileForTypeDTO> listLocationProfile = locationService.getLocationProfileForOneCategory(id);
        return new CategoryResponseDTO(placeCategory.getId(), placeCategory.getName(), listLocationProfile);
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

//    public ArrayList<CategoryResponseDTO> findAllCategoryDetailOfOneType(Long id) {
//        ArrayList<CategoryResponseDTO> listCTDTO = new ArrayList<>();
//        List<PlaceCategory> listPlaceCategoty = findAllPlaceCategoryOfOneType(id);
//        for(PlaceCategory placeCategory : listPlaceCategoty) {
//            List<LocationProfileDTO> listLocationOfOneCategory = locationService.findTop10LocationProfileByCategoryId(placeCategory.getId());
//            listCTDTO.add(new CategoryResponseDTO(placeCategory.getId(),placeCategory.getName(),listLocationOfOneCategory));
//        }
//        return listCTDTO;
//    }

    public ArrayList<CategoryResponseDTO> findAllCategoryDetailOfOneType(Long id) {
        ArrayList<CategoryResponseDTO> listCTDTO = new ArrayList<>();
        List<PlaceCategory> listPlaceCategoty = findAllPlaceCategoryOfOneType(id);
        for(PlaceCategory placeCategory : listPlaceCategoty) {
            List<LocationProfileForTypeDTO> listLocationOfOneCategory = locationService.findTop10LocationProfileByCategoryId(placeCategory.getId());
            listCTDTO.add(new CategoryResponseDTO(placeCategory.getId(),placeCategory.getName(),listLocationOfOneCategory));
        }
        return listCTDTO;
    }
}
