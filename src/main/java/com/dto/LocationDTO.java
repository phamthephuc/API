package com.dto;

import com.entity.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class LocationDTO {
    Long id;
    String name;
    String introduction;
    Date createdDate;
    PlaceCategory placeCategory;
    Content content;
    Status  status;
    Address address;
    Contact contact;
    Users users;
    Duration duration;
    List<Picture> pictureList;


}
