package com.model;


import lombok.Data;

@Data

public class LocationRequest {

    String name;
    String introduction;
    Long idPlaceCategory;
    String content;
    Long idStatus;
    Float longitudeAddress;
    Float latitudeAddress;
    String nameAddress;
    String phone;
    String email;
    Long idDuration;

}
