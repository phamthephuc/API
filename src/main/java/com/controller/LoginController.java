package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Traveler;
import com.service.TravelerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    TravelerService travelerService;

    @PostMapping("/login")
    public APIResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
        return new APIResponseDTO(200, "Login success!", travelerService.login(username, password));
    }
}