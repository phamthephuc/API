package com.model;


import lombok.Data;

@Data

public class LocationRequest {

    String name;
    String introduction;
    Long idPlaceType;
    Long idPlaceCategory;
    String content;
    Long idStatus;
    Double longitudeAddress;
    Double latitudeAddress;
    String nameAddress;
    String phone;
    String email;
    Long idDuration;

}
