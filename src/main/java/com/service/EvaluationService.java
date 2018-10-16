package com.service;

import com.entity.Evaluation;
import com.entity.PlaceCategory;
import com.repository.EvaluationRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;

    public List<Evaluation> findAllEvaluation(){
        return (List<Evaluation>) evaluationRepository.findAll();
    }

    public Optional<Evaluation> findById(Long id){
        return evaluationRepository.findById(id);
    }

    public  void createEvaluation(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }

    public void updateEvaluation(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(Long id){
        evaluationRepository.deleteById(id);
    }
}
