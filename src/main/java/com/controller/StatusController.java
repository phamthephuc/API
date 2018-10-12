package com.controller;


import com.dto.APIResponseDTO;
import com.entity.Status;
import com.service.StatusService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
public class StatusController {
    @Autowired
    StatusService statusService;
    @GetMapping( value = "/status")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public APIResponseDTO getAllStatus(){
        return new APIResponseDTO(200,"Success!",statusService.findAll());
    }
    @GetMapping(value = "/status/{id}")
    public  APIResponseDTO getStatus(@PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",statusService.findById(id));
    }
    @PostMapping(value = "/status")
    public APIResponseDTO createStatus(@RequestBody Status status){
        statusService.createStatus(status);
        return  new APIResponseDTO(201,"Created!",status);
    }
    @PutMapping(value = "/status/{id}")
    public ResponseEntity<Object> editStatus(@RequestBody Status status, @PathVariable Long id){
        Optional<Status> statusOptional = statusService.findById(id);
        if (!statusOptional.isPresent()) return ResponseEntity.notFound().build();
        status.setId(id);
        statusService.updateStatus(status);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/status/{id}")
    public APIResponseDTO deleteStudent(@PathVariable long id) {
        statusService.deleteStatus(id);
        return  new APIResponseDTO(201,"Deleted!",null);
    }
}
