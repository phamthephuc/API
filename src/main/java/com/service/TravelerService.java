package com.service;

import com.config.JwtTokenProvider;
import com.entity.Traveler;
import com.exception.CustomException;
import com.repository.RoleRespository;
import com.repository.TravelerResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TravelerService {
    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    RoleRespository roleRespository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Traveler findByEmail(String username){
        return  travelerResponsitory.findByUsername(username);
    }

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", 422);
//            e.printStackTrace();
//            return  "false";
        }
    }

    public Traveler addTraveler(Traveler travelerNew) {
        travelerNew.setPassword(passwordEncoder.encode(travelerNew.getPassword()));
        travelerNew.setRoleId(roleRespository.findByName("user").getId());
        return travelerResponsitory.save(travelerNew);
    }
}
