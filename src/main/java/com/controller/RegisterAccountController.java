package com.controller;

import com.anotherAPI.ResponseOtherApi;
import com.dto.APIResponseDTO;
import com.dto.UserRegisterDTO;
import com.entity.InforUsers;
import com.entity.Traveler;
import com.service.InforUsersService;
import com.service.RecommendService;
import com.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
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

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String uri = ResponseOtherApi.urlRecommendServer + "/addUser";
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
                    params.add("id_user", travelerAdded.getId());
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.add("passcode", RecommendService.passcode);
                    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                            new HttpEntity<>(params, headers);
                    ResponseEntity<ResponseOtherApi> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ResponseOtherApi.class);
                }
            }).start();

            return new APIResponseDTO(200,"Signup success!", travelerAdded.getUsername());
        } else {
            return new APIResponseDTO(500, "Account same username exist!", null);
        }
    }
}
