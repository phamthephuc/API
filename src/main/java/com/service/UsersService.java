package com.service;


import com.config.JwtTokenProvider;
import com.dto.UsersProfileResponse;
import com.entity.InforUsers;
import com.entity.Traveler;
import com.entity.Users;
import com.repository.InforUsersRepository;
import com.repository.TravelerResponsitory;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    InforUsersRepository inforUsersRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public List<Users> listUserRelative(Long idUser) {
        return usersRepository.findAllUserSameLocationWithOnes(idUser);
    }

    public UsersProfileResponse getUsersProfile(HttpServletRequest request){
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        InforUsers inforUsers = inforUsersRepository.getInforUsersByIdUser(travelerCurrent.getId());
        UsersProfileResponse usersProfileResponse = new UsersProfileResponse();
        usersProfileResponse.setUsername(travelerCurrent.getUsername());
        usersProfileResponse.setAddress(inforUsers.getAddress());
        usersProfileResponse.setGender(inforUsers.getGender());
        usersProfileResponse.setPhone(inforUsers.getPhone());
        usersProfileResponse.setFullname(inforUsers.getFullname());
        return usersProfileResponse;

    }

    public Users findUserFromToken(HttpServletRequest request) {
        return usersRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
    }
}