package com.service;


import com.entity.Status;
import com.entity.Users;
import com.repository.StatusRepository;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> listUserRelative(Long idUser) {
        return usersRepository.findAllUserSameLocationWithOnes(idUser);
    }

}