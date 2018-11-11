package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Picture;
import com.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("/api/upload-image-for-location")
    public APIResponseDTO uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idLocation") Long idLocation) {
        Picture pictureAdded = fileStorageService.storeFile(file, idLocation);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());



        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        String responInfor =  fileName + " " +  fileDownloadUri + " " + file.getContentType() + " " + file.getSize();
        return new APIResponseDTO(200,"Created!", pictureAdded );
    }


//    @PostMapping("/uploadFileNonLocation")
//    public String uploadFileNonLocation(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//
//        return fileName + " " +  fileDownloadUri + " " + file.getContentType() + " " + file.getSize();
//
//    }

    //    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }
//
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        File fileImage = resource.getFile();

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
//        return  ResponseEntity.ok()
//                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(fileImage)))
//                .body(Files.readAllBytes(fileImage.toPath()));
    }
}