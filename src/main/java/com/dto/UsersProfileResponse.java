package com.dto;

import lombok.Data;

@Data

public class UsersProfileResponse {

    String username;
    String address;
    String phone;
    Long gender;
    String fullname;
}
