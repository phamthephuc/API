package com.service;

import com.config.JwtTokenProvider;
import com.entity.Traveler;
import com.exception.CustomException;
import com.repository.TravelerResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class TravelerService {
    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

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
}
