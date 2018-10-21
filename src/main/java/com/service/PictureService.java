package com.service;


import com.entity.Picture;
import com.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class PictureService {
//
//    @Autowired
//    PictureRepository pictureRepository;
//
//    public List<Picture> findAllPicture(){
//        return (List<Picture>) pictureRepository.findAll();
//    }
//
//    public Optional<Picture> findById(Long id){
//        return pictureRepository.findById(id);
//    }
//
//    public  void createPicture(Picture picture){
//        pictureRepository.save(picture);
//    }
//
//    public void updatePicture(Picture picture){
//        pictureRepository.save(picture);
//    }
//
//    public void deletePicture(Long id){
//        pictureRepository.deleteById(id);
//    }

    private  static String UPLOAD_ROOT = "src/main/resources/images";
    private  final  PictureRepository repository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public PictureService(PictureRepository pictureRepository, ResourceLoader resourceLoader){
        this.repository = pictureRepository;
        this.resourceLoader = resourceLoader;
    }

    public Resource findOnePicture(String filename){
        return resourceLoader.getResource("file:" +UPLOAD_ROOT+"/"+filename);
    }

    public void createPicture (MultipartFile file , Long idLocation) throws IOException {
        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            repository.save(new Picture(100, file.getOriginalFilename(), file.getOriginalFilename(), idLocation));
        }
    }

    public  Picture findByFileName(String filename){
        return  repository.findByName(filename);
    }

    public void deletePicture (Long  id) throws IOException {
        final  Picture byName = repository.findById(id).orElse(new Picture(100,"Null","Null", (long) 100));
        repository.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, byName.getName()));
    }


}
