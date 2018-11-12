package com.controller;

import com.dto.APIResponseDTO;
import com.entity.PlaceCategory;
import com.entity.PlaceType;
import com.service.PlaceCategoryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class PlaceCategoryController {

    @Autowired
    PlaceCategoryService placeCategoryService;

    @GetMapping(value = "/place-categories")
//    @PreAuthorize("hasAuthority('user')")
    public APIResponseDTO findAll(){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findAllPlaceCategory());
    }

    @GetMapping(value = "/place-type/{id}/place-categories")
//    @PreAuthorize("hasAuthority('user')")
    public  APIResponseDTO findAllPlaceCategoryOfOneType(@PathVariable Long id){
        return  new APIResponseDTO(200,"Success", placeCategoryService.findAllPlaceCategoryOfOneType(id));
    }

    @GetMapping(value = "/place-category/{id}")
    public  APIResponseDTO getPlaceCategory( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findById(id));
    }

    @GetMapping(value = "app/place-category/{id}")
    public  APIResponseDTO getInfoPlaceCategory( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findCategoryInfoForApp(id));
    }

    @PostMapping(value = "/place-category")
    public APIResponseDTO  createPlaceCategory(@RequestBody PlaceCategory placeCategory){
        placeCategoryService.createPlaceCategory(placeCategory);
        return  new APIResponseDTO(201,"Created!",placeCategory);
    }

    @PutMapping(value = "/place-category/{id}")
    public APIResponseDTO editPlaceCategory(@RequestBody PlaceCategory placeCategory, @PathVariable Long id){
        PlaceCategory placeCategoryOld = placeCategoryService.findById(id).orElse(new PlaceCategory());
        System.out.print(placeCategory.getIdPlaceType());
        if (placeCategoryOld == null) return new APIResponseDTO(200, "Not Existed!", null);
        placeCategoryOld.setName(placeCategory.getName());
        placeCategoryService.updatePlaceCategory(placeCategoryOld);
        return new APIResponseDTO(200, "Edited", placeCategoryOld);

    }

    @DeleteMapping(value = "/place-categories/{id}")
    public APIResponseDTO deletePlaceCategory(@PathVariable long id) {
        placeCategoryService.deletePlaceCategory(id);
        return  new APIResponseDTO(200,"Deleted!", null);

    }




}
