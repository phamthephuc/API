package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Picture;
import com.service.FileStorageService;
import com.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Path;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class PictureController {
    @Autowired
    PictureService pictureService;

    @Autowired
    FileStorageService fileStorageService;


    // old function
    @RequestMapping(value = "/upload-picture", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO uploadPicture(@RequestParam("file") MultipartFile file) throws IOException {

        pictureService.createPicture(file, (long) 1000);
        return new APIResponseDTO(200,"Succesd", pictureService.findByFileName(file.getOriginalFilename()));

    }

    // old function
    @RequestMapping(value = "/add-picture-for-location", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO addPicture(@RequestParam("file") MultipartFile file, @RequestParam("idLocation") Long idLocation) throws IOException {

        pictureService.createPicture(file, idLocation);
        return new APIResponseDTO(200,"Succesd", pictureService.findByFileName(file.getOriginalFilename()));

    }

    // old function
    @RequestMapping(value = "/add-picture", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO addNewPicture(@RequestParam("file") MultipartFile file, @RequestParam("idLocation") Long idLocation) throws IOException {
        return new APIResponseDTO(200,"Succesd", pictureService.storeFile(file, idLocation));

    }

//    @PostMapping("/api/uploadFile")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/images/")
//                .path(fileName)
//                .toUriString();
//
//        return fileName + " " +  fileDownloadUri + " " + file.getContentType() + " " + file.getSize();
//    }

    @GetMapping(value = "/api/location/{idLocation}/pictures")
    public  APIResponseDTO listPicturesOfLocation(@PathVariable Long idLocation){
        return new APIResponseDTO(200,"Success", pictureService.listPictureOfLocation(idLocation));
    }

    @DeleteMapping(value = "/api/delete-picture/{idPicture}")
    public APIResponseDTO deletePictureById(@PathVariable Long idPicture) throws IOException {
        pictureService.deletePicture(idPicture);
        return new APIResponseDTO(200, "Deleted!", null);
    }






}