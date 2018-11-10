package com.controller;

import com.dto.APIResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @GetMapping(value = "/api/user-prfile")
    public APIResponseDTO getUserProfile(){

        return new APIResponseDTO();
    }



}
