package com.service;

import com.config.JwtTokenProvider;
import com.entity.Favorite;
import com.entity.PlaceCategory;
import com.entity.Traveler;
import com.repository.FavoriteRepository;
import com.repository.PlaceCategoryRepository;
import com.repository.TravelerResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public List<Favorite> findAllFavorite(){
        return (List<Favorite>) favoriteRepository.findAll();
    }

    public Optional<Favorite> findById(Long id){
        return favoriteRepository.findById(id);
    }

    public  void createFavorite(Favorite favorite){
        favoriteRepository.save(favorite);
    }

    public void updateFavorite(Favorite favorite){
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long id){
        favoriteRepository.deleteById(id);
    }

    public Boolean setFavorite(Long idLocation, HttpServletRequest request) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Favorite favorite = favoriteRepository.findByIdUserAndIdLocation(travelerCurrent.getId(), idLocation);
        if (favorite == null ){
            Favorite newFavorite = new Favorite();
            newFavorite.setIdLocation(idLocation);
            newFavorite.setIdUser(travelerCurrent.getId());
            favoriteRepository.save(newFavorite);
            return true;
        } else {
            favoriteRepository.delete(favorite);
            return false;
        }
    }
}
