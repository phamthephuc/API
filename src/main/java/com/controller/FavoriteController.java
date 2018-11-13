package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Favorite;
import com.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/api/app/favorite-location")
    public APIResponseDTO setFavoriteLocation(HttpServletRequest request, @RequestParam Long idLocation){
        Boolean status = favoriteService.setFavorite(idLocation, request);
        return new APIResponseDTO(200,"OK", status);
    }



}
