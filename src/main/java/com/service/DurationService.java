package com.service;

import com.entity.Duration;
import com.entity.PlaceCategory;
import com.repository.DurationRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DurationService {

    @Autowired
    DurationRepository durationRepository;

    public List<Duration> findAllDuration(){
        return (List<Duration>) durationRepository.findAll();
    }

    public Optional<Duration> findById(Long id){
        return durationRepository.findById(id);
    }

    public  void createDuration(Duration duration){
        durationRepository.save(duration);
    }

    public void updateDuration(Duration duration){
        durationRepository.save(duration);
    }

    public void deleteDuration(Long id){
        durationRepository.deleteById(id);
    }
}
