package com.dto;

import com.entity.*;
import lombok.Data;

import java.util.Date;

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
    User user;
    Duration duration;


}
