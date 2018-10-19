package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Location;
import com.entity.PlaceCategory;
import com.service.LocationService;
import com.service.PlaceCategoryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@RestController


public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping(value = "/locations")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findAll(){
        return  new APIResponseDTO(200,"Success!",locationService.findAllLocation());
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("\resources\\images\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout =new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is upload Seccessfully", HttpStatus.OK);
    }

    @GetMapping(value = "/location/{id}")
    public  APIResponseDTO getLocation( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",locationService.findById(id));
    }

    @GetMapping(value = "/location-by-category/{id}")
    public  APIResponseDTO getLocationByCategory(@PathVariable Long id){

        return  new APIResponseDTO(200, "Success", locationService.findAllLocationByCategoryId(id));
    }

    @GetMapping(value = "/location-profile-by-category/{id}")
    public  APIResponseDTO getLocationProfileByCategory(@PathVariable Long id){

        return  new APIResponseDTO(200, "Success", locationService.findAllLocationProfileByCategoryId(id));
    }

    @PostMapping(value = "/location")
    public APIResponseDTO  createLocation(@RequestBody Location location){
        locationService.createLocation(location);
        return  new APIResponseDTO(201, "Created!",location);
    }

    @PutMapping(value = "/location/{id}")
    public ResponseEntity<Object> editLocation(@RequestBody Location location, @PathVariable Long id){
        Optional<Location> locationOld = locationService.findById(id);
        if (!locationOld.isPresent()) return ResponseEntity.notFound().build();
        location.setId(id);
        locationService.updateLocation(location);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/location/{id}")
    public APIResponseDTO deleteLocation(@PathVariable long id) {
        locationService.deleteLocation(id);
        return  new APIResponseDTO(200,"Deleted!", null);

    }

    @GetMapping(value = "/location/user-evaluation/{id}")
    public APIResponseDTO getLocationOfUserEvaluation(@PathVariable long id){
        return new APIResponseDTO(200, "Success",locationService.findAllLocationOfUserEvaluation(id));
    }
}
