package com.dto;

import lombok.Data;

@Data

public class UsersProfileDTO {
    Long idUser;
    String username;
    String address;
    String phone;
    Long gender;
    String fullname;
}
