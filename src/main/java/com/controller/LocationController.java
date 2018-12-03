package com.controller;

import com.dto.APIResponseDTO;
import com.dto.LocationProfileDTO;
import com.dto.TypeResponseDTO;
import com.entity.Address;
import com.entity.Location;
import com.entity.Users;
import com.model.LocationRequest;
import com.service.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class LocationController {

    @Autowired
    LocationService locationService;

    @Autowired
    PictureService pictureService;

    @Autowired
    RecommendService recommendService;

    @Autowired
    AddressService addressService;

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/api/location/{idLocation}")
    public APIResponseDTO getLocationById(@PathVariable Long idLocation){
        return new APIResponseDTO(200, "OK", locationService.getLocationRequestById(idLocation));
    }



    @PreAuthorize("hasAuthority('admin') or  hasAuthority('mod')")
    @GetMapping(value = "/locations/{currentPage}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findAllLocationPagination(HttpServletRequest request, @PathVariable int currentPage){
        return  new APIResponseDTO(200,"Success!",locationService.findAllLocationPagination(request, currentPage));
    }

    @GetMapping(value = "/locations/{idCategory}/{currentPage}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findAllLocationInOneCategoryPagination(@PathVariable Long idCategory, @PathVariable int currentPage){
        return  new APIResponseDTO(200,"Success!",locationService.findAllLocationInOneCategoryPagination(currentPage,idCategory));
    }


//    @GetMapping(value = "/locations/recommends/{idUser}")
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//    public APIResponseDTO findRecommend(@PathVariable Long idUser){
//        return  new APIResponseDTO(200,"Success!",recommendService.getListLocationProfileDTORecommend(idUser));
//    }

    @GetMapping(value = "/top-10-new-locations")
//    @PreAuthorize("hasAuthority('user')")

    public APIResponseDTO getNewLocations(){
        return new APIResponseDTO(200,"Get All New Locations", locationService.getNewLocations());

    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("\resources\\images\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is upload Seccessfully", HttpStatus.OK);
    }


    @GetMapping("/show-picture")
    public ResponseEntity<byte[]> getImage(@RequestParam("nameImage") String nameImage) throws IOException{
        String name = "src/main/resources/images";

        File img = new File("src/main/resources/images/pratice.jpg");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
                .body(Files.readAllBytes(img.toPath()));
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


    @PostMapping(value = "/create-location-non-picture")
    public  APIResponseDTO createNewLocation(HttpServletRequest request, @RequestBody LocationRequest locationRequest) throws IOException {
        locationService.createNewLocation(locationRequest, request);
        LocationProfileDTO locationCreated = locationService.getLocationLastest();

        return  new APIResponseDTO(200, "Created", locationCreated);
    }


    @PreAuthorize("hasAuthority('admin') or  hasAuthority('mod')")
    @PutMapping(value = "/web/update-location/{idLocation}")
    public APIResponseDTO updateLocation(@RequestBody LocationRequest locationRequest, @PathVariable Long idLocation){

        LocationProfileDTO locationOld = locationService.findById(idLocation);
        if (locationOld == null ) return new APIResponseDTO(500, "Not existed", null);
        else {
            return  new APIResponseDTO(200, "Edited", locationService.editLocation(locationRequest, idLocation));

        }

    }




    @PutMapping(value = "/location/{id}")
    public ResponseEntity<Object> editLocation(@RequestBody LocationRequest LocationRequest, @PathVariable Long id){
        LocationProfileDTO locationOld = locationService.findById(id);
        if (locationOld == null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/location/{id}")
    public APIResponseDTO deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return  new APIResponseDTO(200,"Deleted!", null);

    }

    @GetMapping(value = "/location/user-evaluation/{id}")
    public APIResponseDTO getLocationOfUserEvaluation(@PathVariable long id){
        return new APIResponseDTO(200, "Success",locationService.findAllLocationOfUserEvaluation(id));
    }

    @GetMapping(value = "/app/location/top-10-highlight")
    public APIResponseDTO getLocationOfUserEvaluation(){
        return new APIResponseDTO(200, "Success",locationService.findTop10ByRating());
    }

    @GetMapping(value = "/api/app/locations/user-like")
    public APIResponseDTO getLocationUserCurrentLike(HttpServletRequest request){
        return  new APIResponseDTO(200, "OK", locationService.getLocationUserCurrentLike(request));
    }

    @GetMapping(value = "/app/location/{id}")
    public APIResponseDTO getLocationOfUserEvaluation(HttpServletRequest request, @PathVariable Long id){
        // tim ra User ơ day
        Users usersCurrent = usersService.findUserFromToken(request);
        if (usersCurrent.getId() != null) {
            return new APIResponseDTO(200, "Success",locationService.findDetailLocationById(id,usersCurrent.getId()));
        } else {
            Long idUser = 1L;
            return new APIResponseDTO(200, "Success",locationService.findDetailLocationById(id,idUser));
        }
    }

    @GetMapping(value = "/type-place", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO getAllLocationByPlaceTypeId(@RequestParam("id") Long id) {
        TypeResponseDTO typeResponseDTO = new TypeResponseDTO();
//        typeResponseDTO = locationService.....
        return  new APIResponseDTO();
    }

    @DeleteMapping(value = "/api/delete-location/{idLocation}")
    public APIResponseDTO deleteLocationById(@PathVariable Long idLocation){
        locationService.deleteLocation(idLocation);
        return new APIResponseDTO(200,"Deleted!",null);
    }

    //temprary

    @PutMapping(value = "/api/edit-address/{idLocation}")
    public APIResponseDTO editAddressOfLocationByLocationId(@PathVariable Long idLocation, @RequestBody Address address ){
      Address addressEdited = addressService.editAddressOfLocation(idLocation, address);
      return new APIResponseDTO(200,"Edited", addressEdited);

    }

}
