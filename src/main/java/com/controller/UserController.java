package com.controller;

import com.dto.APIResponseDTO;
import com.dto.EvaluationDTO;
import com.dto.UsersProfileResponse;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/api/user-profile")
    public APIResponseDTO getUserProfile(HttpServletRequest request){
        UsersProfileResponse usersProfileResponse = usersService.getUsersProfile(request);
        return new APIResponseDTO(200, "OK", usersProfileResponse);
    }

}
