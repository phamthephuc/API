package com.service;

import com.config.JwtTokenProvider;
import com.dto.EvaluationDTO;
import com.dto.EvaluationPaginationDTO;
import com.dto.ReviewDTO;
import com.entity.Evaluation;
import com.entity.InforUsers;
import com.entity.Traveler;
import com.entity.Users;
import com.repository.EvaluationRepository;
import com.repository.TravelerResponsitory;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    UsersRepository usersRepository;

    public static final int PAGE_SIZE = 5;
    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public List<Evaluation> findAllEvaluation(){
        return (List<Evaluation>) evaluationRepository.findAll();
    }

    public Optional<Evaluation> findById(Long id){
        return evaluationRepository.findById(id);
    }

    public List<Evaluation> findAllEvaluationByIdUser(Long idUser) {
        return evaluationRepository.findAllByIdUser(idUser);
    }

    public  void createEvaluation(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }

    public List<Evaluation> findAllRatingWithTheSameLocationOfTwoUser(Long idUserRecommend, Long idUser2) {
        return evaluationRepository.findAllRatingWithTheSameLocationOfTwoUser(idUserRecommend,idUser2);
    }

    public void updateEvaluation(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(Long id){
        evaluationRepository.deleteById(id);
    }

    public EvaluationPaginationDTO findAppReviewDTOPagination(Long idLocation, int crrPage) {
        PageRequest pageRequest = new PageRequest(crrPage - 1, PAGE_SIZE, Sort.Direction.DESC,"id");
        Page<Evaluation> evaluationPage = evaluationRepository.findAllByIdLocation(idLocation,pageRequest);
        return findAppReviewDTOFromEvaluation(evaluationPage);
    }

    public List<EvaluationDTO> getListEvaluationDTOFromListEvaluation(List<Evaluation> listEvaluation) {
        List<EvaluationDTO> listEvaluationDTO = new ArrayList<>();
        for (Evaluation evaluation : listEvaluation) {
            EvaluationDTO evaluationDTO = getEvaluationDTOFromEvaluation(evaluation);
            listEvaluationDTO.add(evaluationDTO);
        }
        return listEvaluationDTO;
    }

    public EvaluationDTO getEvaluationDTOFromEvaluation(Evaluation evaluation) {
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setContent(evaluation.getContent());
        evaluationDTO.setDateReview(evaluation.getEvaluationDate());
        evaluationDTO.setScore(evaluation.getScore());
//        String username = usersRepository.findById(evaluation.getId()).get().getUsername();
        Users usersCurrent = usersRepository.findById(evaluation.getIdUser()).orElse(new Users());
        evaluationDTO.setNameUser(usersCurrent.getUsername());
        return evaluationDTO;
    }

    public EvaluationPaginationDTO findAppReviewDTOFromEvaluation(Page<Evaluation> evaluationPage) {
        EvaluationPaginationDTO evaluationPaginationDTO = new EvaluationPaginationDTO();
        List<Evaluation> listEvaluation = evaluationPage.getContent();
        List<EvaluationDTO> listEvaluationDTO = getListEvaluationDTOFromListEvaluation(listEvaluation);
        evaluationPaginationDTO.setCrrPage(evaluationPage.getNumber() + 1);
        evaluationPaginationDTO.setSumPage(evaluationPage.getTotalPages());
        evaluationPaginationDTO.setListEvaluation(listEvaluationDTO);
        return evaluationPaginationDTO;
    }

    //Gui kem userId
    public Evaluation reviewLocation(@RequestBody ReviewDTO reviewDTO){
        Evaluation evaluation = new Evaluation();
        evaluation.setIdUser(reviewDTO.getUserId());
        evaluation.setScore(reviewDTO.getNumberRating());
        evaluation.setIdLocation(reviewDTO.getLocationId());
        evaluation.setContent(reviewDTO.getCommentContent());
        evaluation.setEvaluationDate(new Date());
        Evaluation evaluationAdded = evaluationRepository.save(evaluation);
        return evaluationAdded;
    }

    //user da login, ko can gui kem idUser
    public Evaluation reviewLocation(HttpServletRequest request, ReviewDTO reviewDTO) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Evaluation evaluation = new Evaluation();
        evaluation.setIdUser(travelerCurrent.getId());
        evaluation.setScore(reviewDTO.getNumberRating());
        evaluation.setIdLocation(reviewDTO.getLocationId());
        evaluation.setContent(reviewDTO.getCommentContent());
        evaluation.setEvaluationDate(new Date());
        Evaluation evaluationAdded = evaluationRepository.save(evaluation);
        return evaluationAdded;
    }
}
