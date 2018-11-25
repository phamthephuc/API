package com.dto;

import lombok.Data;

@Data

public class UsersProfileDTO {
    Long idUser;
    String username;
    Long status;
    String address;
    String phone;
    Long gender;
    String fullname;
    String roleName;

}
