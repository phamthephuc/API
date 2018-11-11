package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Favorite;
import com.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/app/favorite-location/{idLocation}")
    public APIResponseDTO setFavoriteLocation(@PathVariable Long idLocation){
        Long idUser = 1L;
        Favorite favorite = new Favorite();
        favorite.setIdUser(idUser);
        favorite.setIdLocation(idLocation);
        favoriteService.createFavorite(favorite);
        return new APIResponseDTO(200,"OK", null);
    }

}
