package com.service;

import com.anotherAPI.ResponseOtherApi;
import com.config.JwtTokenProvider;
import com.dto.*;
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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    public EvaluationWebPaginationDTO findWebReviewDTOFromEvaluation(Page<Evaluation> evaluationPage) {
        EvaluationWebPaginationDTO   evaluationWebPaginationDTO = new EvaluationWebPaginationDTO();
        List<Evaluation> listEvaluation = evaluationPage.getContent();
        List<EvaluationWebDTO> listEvaluationWebDTO = getListEvaluationWebDTOFromListEvaluation(listEvaluation);
        evaluationWebPaginationDTO.setCrrPage(evaluationPage.getNumber() + 1);
        evaluationWebPaginationDTO.setSumPage(evaluationPage.getTotalPages());
        evaluationWebPaginationDTO.setListEvaluationWeb(listEvaluationWebDTO);
        return evaluationWebPaginationDTO;
    }

    public List<EvaluationWebDTO> getListEvaluationWebDTOFromListEvaluation(List<Evaluation> listEvaluation) {
        List<EvaluationWebDTO> evaluationWebDTOS = new ArrayList<>();
        for (Evaluation evaluation : listEvaluation) {
            EvaluationWebDTO evaluationWebDTO = getEvaluationWebDTOFromEvaluation(evaluation);
            evaluationWebDTOS.add(evaluationWebDTO);
        }
        return evaluationWebDTOS;
    }

    private EvaluationWebDTO getEvaluationWebDTOFromEvaluation(Evaluation evaluation) {
        EvaluationWebDTO evaluationWebDTO= new EvaluationWebDTO();
        evaluationWebDTO.setId(evaluation.getId());
        evaluationWebDTO.setContent(evaluation.getContent());
        evaluationWebDTO.setDateReview(evaluation.getEvaluationDate());
        evaluationWebDTO.setScore(evaluation.getScore());
//        String username = usersRepository.findById(evaluation.getId()).get().getUsername();
        Users usersCurrent = usersRepository.findById(evaluation.getIdUser()).orElse(new Users());
        evaluationWebDTO.setNameUser(usersCurrent.getUsername());
        return evaluationWebDTO;
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
    public void callApiAddEvaluationFromRecommendServer(Traveler travelerCurrent, ReviewDTO reviewDTO) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String uri = ResponseOtherApi.urlRecommendServer + "/addEvaluation";
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
                params.add("id_user", travelerCurrent.getId());
                params.add("id_location", reviewDTO.getLocationId());
                params.add("score", reviewDTO.getNumberRating());
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                headers.add("passcode", RecommendService.passcode);
                HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                        new HttpEntity<>(params, headers);
                ResponseEntity<ResponseOtherApi> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ResponseOtherApi.class);
            }
        }).start();
    }
    //user da login, ko can gui kem idUser
    public Evaluation reviewLocationForApp(HttpServletRequest request, ReviewDTO reviewDTO) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Evaluation evaluationOld = evaluationRepository.findEvaluationByIdUserAndIdLocation(travelerCurrent.getId(), reviewDTO.getLocationId());
        callApiAddEvaluationFromRecommendServer(travelerCurrent,reviewDTO);
        if (evaluationOld == null) {
            Evaluation evaluation = new Evaluation();
            evaluation.setIdUser(travelerCurrent.getId());
            evaluation.setScore(reviewDTO.getNumberRating());
            evaluation.setIdLocation(reviewDTO.getLocationId());
            evaluation.setContent(reviewDTO.getCommentContent());
            evaluation.setEvaluationDate(new Date());
            Evaluation evaluationAdded = evaluationRepository.save(evaluation);
            return evaluationAdded;
        } else {
            evaluationOld.setScore(reviewDTO.getNumberRating());
            evaluationOld.setContent(reviewDTO.getCommentContent());
            evaluationOld.setEvaluationDate(new Date());
            Evaluation evaluationEdited = evaluationRepository.save(evaluationOld);
            return  evaluationEdited;
        }


    }

    public boolean editReviewLocation(HttpServletRequest request, ReviewDTO reviewDTO) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Evaluation evaluationOld = evaluationRepository.findEvaluationByIdUserAndIdLocation(travelerCurrent.getId(), reviewDTO.getLocationId());
        callApiAddEvaluationFromRecommendServer(travelerCurrent,reviewDTO);
        if (evaluationOld != null){
            evaluationOld.setScore(reviewDTO.getNumberRating());
            evaluationOld.setContent(reviewDTO.getCommentContent());
            evaluationRepository.save(evaluationOld);
            return true;
        } else {
            return false;
        }
    }

    public Evaluation getReviewLocation(HttpServletRequest request, Long idLocation) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Evaluation evaluationCurrent = evaluationRepository.findEvaluationByIdUserAndIdLocation(travelerCurrent.getId(), idLocation);
        return  evaluationCurrent;
    }

    public EvaluationWebPaginationDTO findWebReviewDTOPagination(Long idLocation, int crrPage) {
        PageRequest pageRequest = new PageRequest(crrPage - 1, PAGE_SIZE, Sort.Direction.DESC,"id");
        Page<Evaluation> evaluationPage = evaluationRepository.findAllByIdLocation(idLocation,pageRequest);
        return findWebReviewDTOFromEvaluation(evaluationPage);
    }
}
