package com.controller;

import com.dto.APIResponseDTO;
import com.service.RecommendService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @GetMapping(value = "/locations/recommends/{idUser}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO findRecommend(@PathVariable Long idUser){
        return  new APIResponseDTO(200,"Success!",recommendService.getListLocationProfileDTORecommend(idUser));
    }

}
