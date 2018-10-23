package com.controller;

import com.dto.APIResponseDTO;
import com.entity.PlaceCategory;
import com.entity.PlaceType;
import com.service.PlaceCategoryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlaceCategoryController {

    @Autowired
    PlaceCategoryService placeCategoryService;

    @GetMapping(value = "/place-categorys")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findAll(){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findAllPlaceCategory());
    }

    @GetMapping(value = "/place-type/{id}/place-category")
    public  APIResponseDTO findAllPlaceCategoryOfOneType(@PathVariable Long id){
        return  new APIResponseDTO(200,"Success", placeCategoryService.findAllPlaceCategoryOfOneType(id));
    }

    @GetMapping(value = "/place-category/{id}")
    public  APIResponseDTO getPlaceCategory( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findById(id));
    }

    @PostMapping(value = "/place-category")
    public APIResponseDTO  createPlaceCategory(@RequestBody PlaceCategory placeCategory){
        placeCategoryService.createPlaceCategory(placeCategory);
        return  new APIResponseDTO(201,"Created!",placeCategory);
    }

    @PutMapping(value = "/place-category/{id}")
    public APIResponseDTO editPlaceCategory(@RequestBody PlaceCategory placeCategory, @PathVariable Long id){
        Optional<PlaceCategory> placeCategoryOld = placeCategoryService.findById(id);
        if (!placeCategoryOld.isPresent()) return new APIResponseDTO(200, "Not Existed!", null);
        placeCategory.setId(id);
        placeCategoryService.updatePlaceCategory(placeCategory);
        return new APIResponseDTO(200, "Edited", placeCategory);

    }

    @DeleteMapping(value = "/place-categorys/{id}")
    public APIResponseDTO deletePlaceCategory(@PathVariable long id) {
        placeCategoryService.deletePlaceCategory(id);
        return  new APIResponseDTO(200,"Deleted!", null);

    }




}
