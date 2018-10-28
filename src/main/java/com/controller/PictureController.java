package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Picture;
import com.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class PictureController {
    @Autowired
    PictureService pictureService;


    @RequestMapping(value = "/upload-picture", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO uploadPicture(@RequestParam("file") MultipartFile file) throws IOException {

        pictureService.createPicture(file, (long) 1000);
        return new APIResponseDTO(200,"Succesd", pictureService.findByFileName(file.getOriginalFilename()));

    }

    @RequestMapping(value = "/add-picture-for-location", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO addPicture(@RequestParam("file") MultipartFile file, @RequestParam("idLocation") Long idLocation) throws IOException {

        pictureService.createPicture(file, idLocation);
        return new APIResponseDTO(200,"Succesd", pictureService.findByFileName(file.getOriginalFilename()));

    }

    @RequestMapping(value = "/add-picture", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponseDTO addNewPicture(@RequestParam("file") MultipartFile file, @RequestParam("idLocation") Long idLocation) throws IOException {
        return new APIResponseDTO(200,"Succesd", pictureService.storeFile(file, idLocation));

    }


}
