package com.service;


import com.anotherAPI.ResponseOtherApi;
import com.anotherAPI.ResponseRecommend;
import com.dto.LocationProfileDTO;
import com.dto.LocationProfileForTypeDTO;
import com.entity.Evaluation;
import com.entity.Location;
import com.entity.Users;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendService {
    public static final String passcode = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMb2dpbiIsImRhdGEiOnsiaWQiOjEyLCJ1c2VybmFtZSI6ImNhb2RhbzEyMzQzNCJ9LCJpYXQiOjE1NDQxODk5MjMsImV4cCI6MTU0NTA1MzkyM30.P4_BTd-ZuOtvQd0QlazlKigmDl9sVnvoKgjWjXmspdA";
    @Autowired
    UsersService usersService;

    @Autowired
    LocationService locationService;

    @Autowired
    EvaluationService evaluationService;


    public List<LocationProfileForTypeDTO> getListLocationProfileDTORecommend(Long idUser) {
        final String uri = ResponseOtherApi.urlRecommendServer +  "/recommendTravel/" + idUser;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("passcode",passcode);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<ResponseRecommend> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseRecommend.class);
//        final String uri = "http://localhost:5002/recommendTravel/" + idUser;
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseRecommend responseRecommend = (ResponseRecommend) restTemplate.getForObject(uri, ResponseRecommend.class);
//
//        System.out.println("IN KET QUA NHAN DUOC");
//        responseRecommend.printData();
        ResponseRecommend responseRecommend = responseEntity.getBody();
        List<LocationProfileForTypeDTO> listLocationProfile = new ArrayList<>();
        for(int i = 0; i < responseRecommend.data.length; i++) {
            Location location = locationService.locationRepository.findById(responseRecommend.data[i]).orElse(new Location());
            if(location.getId() != null) {
                LocationProfileForTypeDTO locationProfileForTypeDTO = locationService.getLocationProfileForTypeDTOWithLocation(location);
                listLocationProfile.add(locationProfileForTypeDTO);
            }

        }

        return listLocationProfile;

//        List<Evaluation> listEvaluationOfUser = evaluationService.findAllEvaluationByIdUser(idUser);
//
//        if(listEvaluationOfUser.size() == 0) {
//            return locationService.findAllLocationRecommendedWithHeightScorce();
//        }
//
//        long idUserChoose = idUser;
//        // chua check dieu kien khi User chua danh gia
//        List<Users> listRelateUsers = usersService.listUserRelative(idUser);
//
//        long minDistance = (long) (listEvaluationOfUser.size() * 5);
//
//        for (Users users : listRelateUsers) {
//            List<Evaluation> listEvaluationRelate = evaluationService.findAllRatingWithTheSameLocationOfTwoUser(idUser,users.getId());
//            long sumDistance = 0;
//            for(Evaluation evaluation : listEvaluationOfUser) {
//                sumDistance += distance(evaluation, listEvaluationRelate);
//            }
//            if(sumDistance < minDistance) {
//                idUserChoose = users.getId();
//                minDistance = sumDistance;
//            }
//        }
//
//        return locationService.findAllLocationRecommended(idUser,idUserChoose);
    }


    public long distance(Evaluation evaluationOfRelateUser, List<Evaluation> listEvaluation) {
        for (Evaluation evaluation : listEvaluation) {
            if(evaluationOfRelateUser.getIdLocation() == evaluation.getIdLocation()) {
                return (long)Math.abs(evaluationOfRelateUser.getScore() - evaluation.getScore());
            }
        }
        return evaluationOfRelateUser.getScore();
    }
}