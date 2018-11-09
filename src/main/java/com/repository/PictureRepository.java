package com.repository;

import com.entity.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PictureRepository extends CrudRepository<Picture, Long> {
    public ArrayList<Picture> findByIdLocation(Long id);
    public  void deleteByIdLocation(Long idLocation);
    public  Picture findByName(String filename);
}
