package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Traveler;
import com.service.TravelerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class SignupController {

    @Autowired
    TravelerService travelerService;

    @PostMapping("/signup")
    public APIResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {

        Traveler traveler = travelerService.findByEmail(username);
        if (traveler==null){
            Traveler travelerNew = new Traveler();
            travelerNew.setUsername(username);
            travelerNew.setPassword(password);
            Traveler travelerAdded = travelerService.addTraveler(travelerNew);
            return  new APIResponseDTO(200, "Sign up success!", travelerAdded);
        } else {
            return new APIResponseDTO(500, "Account same username exist!", null);
        }

    }

}
