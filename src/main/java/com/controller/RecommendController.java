package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Users;
import com.service.RecommendService;
import com.service.UsersService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @Autowired
    UsersService usersService;
    @GetMapping(value = "/locations/recommends")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findRecommend(HttpServletRequest request){
//        System.out.println("COME HERE");
//        return  new APIResponseDTO(200,"Success!",recommendService.getListLocationProfileDTORecommend(5L));
        Users userCurrent = usersService.findUserFromToken(request);

        if (userCurrent != null){
            return  new APIResponseDTO(200,"Success!",recommendService.getListLocationProfileDTORecommend(userCurrent.getId()));
        } else {
            return  new APIResponseDTO(500,"Token wrong!",null);
        }
    }



}
