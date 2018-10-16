package com.service;

import com.entity.Content;
import com.entity.PlaceCategory;
import com.repository.ContentRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    public List<Content> findAllContent(){
        return (List<Content>) contentRepository.findAll();
    }

    public Optional<Content> findById(Long id){
        return contentRepository.findById(id);
    }

    public  void createContent(Content content){
        contentRepository.save(content);
    }

    public void updateContent(Content content){
        contentRepository.save(content);
    }

    public void deleteContent(Long id){
        contentRepository.deleteById(id);
    }
}
