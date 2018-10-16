package com.service;


import com.entity.Picture;
import com.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    public List<Picture> findAllPicture(){
        return (List<Picture>) pictureRepository.findAll();
    }

    public Optional<Picture> findById(Long id){
        return pictureRepository.findById(id);
    }

    public  void createPicture(Picture picture){
        pictureRepository.save(picture);
    }

    public void updatePicture(Picture picture){
        pictureRepository.save(picture);
    }

    public void deletePicture(Long id){
        pictureRepository.deleteById(id);
    }
}
