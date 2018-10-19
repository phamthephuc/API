package com.dto;

import com.entity.Picture;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class LocationProfileDTO {
    Long id;
    String name;
    String introduction;
    Date createdDate;
    String placeCategory;
    String content;
    String  status;
    String  address;
    String phone;
    String email;
    String usersname;
    Long duration;
    List<Picture> pictureList;
}
