package com.controller;

import com.dto.APIResponseDTO;
import com.entity.PlaceType;
import com.service.PlaceTypeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlaceTypeController {

    @Autowired
    PlaceTypeService placeTypeService;

    @GetMapping( value = "/place-types")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO getPlaceTypes(){
        return new APIResponseDTO(200,"Success!",placeTypeService.findAll());
    }

    @GetMapping(value = "/place-type/{id}")
    public  APIResponseDTO getPlaceType( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeTypeService.findById(id));
    }


    @PostMapping(value = "/place-type")
    public APIResponseDTO createPlaceType(@RequestBody PlaceType placeType){
        placeTypeService.save(placeType);
        return  new APIResponseDTO(201,"Created!",placeType);

    }

    @PutMapping(value = "/place-type/{id}")
    public ResponseEntity<Object>  editPlaceType(@RequestBody PlaceType placeType, @PathVariable Long id){
        Optional<PlaceType> placeType1 = placeTypeService.findById(id);
        if (!placeType1.isPresent()) return ResponseEntity.notFound().build();
        placeType.setId(id);
        placeTypeService.save(placeType);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/place-types/{id}")
    public APIResponseDTO deleteStudent(@PathVariable long id) {
        placeTypeService.deleteById(id);
        return  new APIResponseDTO(201,"Deleted!",null);
    }
}
