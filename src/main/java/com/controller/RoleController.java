package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/web/create-role")
    public APIResponseDTO createNewRole(@PathParam("name") String name){
        Role roleNew = new Role();
        roleNew.setName(name);
        return  new APIResponseDTO(200, "OK", roleService.createNewRole(roleNew));
    }

    @GetMapping("/web/get-all-roles")
    public APIResponseDTO getAllRoles(){
        return new APIResponseDTO(200,"OK", roleService.getAllRoles());
    }

}
