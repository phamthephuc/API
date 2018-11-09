package com.service;

import com.entity.InforUsers;
import com.repository.InforUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InforUsersService {

    @Autowired
    InforUsersRepository inforUsersRepository;

    public InforUsers save(InforUsers inforUsers){
        return inforUsersRepository.save(inforUsers);
    }
}
