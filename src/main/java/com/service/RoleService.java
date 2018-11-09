package com.service;

import com.entity.Role;
import com.repository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService  {
    @Autowired
    RoleRespository roleRespository;

    public Role findById(Long id){
        return roleRespository.findById(id).orElse(new Role());
    }

    public Role createNewRole(Role role){
        return  roleRespository.save(role);
    }

    public List<Role> getAllRoles(){
        return roleRespository.findAll();
    }
}
