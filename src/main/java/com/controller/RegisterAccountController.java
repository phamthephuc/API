package com.controller;

import com.dto.APIResponseDTO;
import com.dto.UserRegisterDTO;
import com.entity.InforUsers;
import com.entity.Traveler;
import com.service.InforUsersService;
import com.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController

public class RegisterAccountController {
    @Autowired
    TravelerService travelerService;

    @Autowired
    InforUsersService inforUsersService;

//    @PostMapping("/signup")
//    public APIResponseDTO signup(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
//        Traveler traveler = travelerService.findByEmail(username);
//        if (traveler==null){
//            Traveler travelerNew = new Traveler();
//            travelerNew.setUsername(username);
//            travelerNew.setPassword(password);
//            Traveler travelerAdded = travelerService.addTraveler(travelerNew);
//            return  new APIResponseDTO(200, "Sign up success!", travelerAdded);
//        } else {
//            return new APIResponseDTO(500, "Account same username exist!", null);
//        }
//
//    }

    @PostMapping("/api/sign-up-new-account")
    public APIResponseDTO signupNewAccount(@RequestBody UserRegisterDTO userRegisterDTO){
        Traveler traveler = travelerService.findByEmail(userRegisterDTO.getUsername());
        if (traveler == null){

            Traveler travelerNew = new Traveler();
            travelerNew.setPassword(userRegisterDTO.getPassword());
            travelerNew.setUsername(userRegisterDTO.getUsername());
            Traveler travelerAdded = travelerService.addTraveler(travelerNew);

            InforUsers inforUsersNew = new InforUsers();
            inforUsersNew.setAddress(userRegisterDTO.getAddress());
            inforUsersNew.setGender(userRegisterDTO.getGender());
            inforUsersNew.setPhone(userRegisterDTO.getPhone());
            inforUsersNew.setIdUser(travelerAdded.getId());
            inforUsersNew.setFullname(userRegisterDTO.getFullname());
            InforUsers inforUsersAdded = inforUsersService.save(inforUsersNew);
            return new APIResponseDTO(200,"Signup success!", travelerAdded.getUsername());
        } else {
            return new APIResponseDTO(500, "Account same username exist!", null);
        }
    }
}
