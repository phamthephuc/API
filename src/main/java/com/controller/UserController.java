package com.controller;

import com.dto.APIResponseDTO;
import com.dto.EvaluationDTO;
import com.dto.UsersProfileResponse;
import com.service.UsersService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/api/user-profile")
    public APIResponseDTO getUserProfile(HttpServletRequest request){
        UsersProfileResponse usersProfileResponse = usersService.getUsersProfile(request);
        return new APIResponseDTO(200, "OK", usersProfileResponse);
    }

    @GetMapping(value = "/api/users/{currentPage}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findAllUserPagination(@PathVariable int currentPage){
        return  new APIResponseDTO(200,"Success!",usersService.findAllUsersPagination(currentPage));
    }

    @PutMapping(value = "/api/web/update-status-of-user")
    public APIResponseDTO updateStatusUser(@PathParam("idUser") Long idUser){
        return new APIResponseDTO(200, "Edited", usersService.updateStatusOfUser(idUser));
    }

}
