package com.service;


import com.config.JwtTokenProvider;
import com.dto.*;
import com.entity.InforUsers;
import com.entity.Location;
import com.entity.Traveler;
import com.entity.Users;
import com.repository.InforUsersRepository;
import com.repository.TravelerResponsitory;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public static final int PAGE_SIZE = 5;

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


    public PageUsersDTO findAllUsersPagination(int currentPage) {
        PageRequest pageRequest = new PageRequest(currentPage - 1, PAGE_SIZE, Sort.Direction.DESC,"id");
        Page<Users> pageLocation = usersRepository.findAll(pageRequest);
        return getPageUsersDTOFromPageUsers(pageLocation);
    }


    public PageUsersDTO getPageUsersDTOFromPageUsers(Page<Users> pageUser) {
        List<Users> listUsers = pageUser.getContent();
        List<UsersProfileDTO> listUserProfileDTO  = getAllUsersProfileDTOWithUsers(listUsers);

        PageUsersDTO pageUsersDTO = new PageUsersDTO();
        pageUsersDTO.setCurrentPage(pageUser.getNumber() + 1);
        pageUsersDTO.setSumPage(pageUser.getTotalPages());
        pageUsersDTO.setUsersProfileDTOList(listUserProfileDTO);
        return pageUsersDTO;
    }

    private List<UsersProfileDTO> getAllUsersProfileDTOWithUsers(List<Users> listUsers) {
        List<UsersProfileDTO> listUsersProfileDTOS = new ArrayList<>();
        for (Users users: listUsers){

            UsersProfileDTO usersProfileDTO = new UsersProfileDTO();
            usersProfileDTO.setIdUser(users.getId());
            usersProfileDTO.setUsername(users.getUsername());

            InforUsers inforUsers= inforUsersRepository.getInforUsersByIdUser(users.getId());

            if (inforUsers != null){
                usersProfileDTO.setAddress(inforUsers.getAddress());
                usersProfileDTO.setFullname(inforUsers.getFullname());
                usersProfileDTO.setGender(inforUsers.getGender());
                usersProfileDTO.setPhone(inforUsers.getPhone());
            }

            listUsersProfileDTOS.add(usersProfileDTO);
        }
        return listUsersProfileDTOS;
    }
}