package com.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data

public class UserRegisterDTO {

    @NotBlank(message = "Username must be asleat 4 charater and maximum 200")
    @Email
    @Size(min=4, max=200)
    private String username;

    @NotBlank
    @Size(min=6, max=30)
    private String password;

    private String address;
    private String phone;
    private Long gender;
}
