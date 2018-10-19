package com.service;


import com.dto.LocationProfileDTO;
import com.entity.Evaluation;
import com.entity.Users;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {
    @Autowired
    UsersService usersService;

    @Autowired
    LocationService locationService;

    @Autowired
    EvaluationService evaluationService;


    public List<LocationProfileDTO> getListLocationProfileDTORecommend(Long idUser) {
        List<Evaluation> listEvaluationOfUser = evaluationService.findAllEvaluationByIdUser(idUser);

        if(listEvaluationOfUser.size() == 0) {
            return locationService.findAllLocationRecommendedWithHeightScorce();
        }

        long idUserChoose = idUser;
        // chua check dieu kien khi User chua danh gia
        List<Users> listRelateUsers = usersService.listUserRelative(idUser);

        long minDistance = (long) (listEvaluationOfUser.size() * 5);

        for (Users users : listRelateUsers) {
            List<Evaluation> listEvaluationRelate = evaluationService.findAllRatingWithTheSameLocationOfTwoUser(idUser,users.getId());
            long sumDistance = 0;
            for(Evaluation evaluation : listEvaluationOfUser) {
                sumDistance += distance(evaluation, listEvaluationRelate);
            }
            if(sumDistance < minDistance) {
                idUserChoose = users.getId();
                minDistance = sumDistance;
            }
        }

        return locationService.findAllLocationRecommended(idUser,idUserChoose);
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