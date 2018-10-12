package com.service;


import com.entity.Status;
import com.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;
    public List<Status> findAll(){
        return (List<Status>) statusRepository.findAll();
    }
    public Optional<Status> findById(Long id){
        return statusRepository.findById(id);
    }
    public  void createStatus(Status status){
        statusRepository.save(status);
    }
    public void updateStatus(Status status){
        statusRepository.save(status);
    }
    public void deleteStatus(Long id){
        statusRepository.deleteById(id);
    }
}