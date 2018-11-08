package com.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data

public class UserRegisterDTO {

    @NotBlank(message = "Username couldn't be blank!")
    @Size(min=4, max=200, message = "Username have lenght between 4 and 200!")
    private String username;

    @NotBlank(message = "Password couldn't be blank!")
    @Size(min=6, max=30, message = "Username have lenght between 4 and 200!")
    private String password;

    @NotBlank(message = "Address couldn't be blank!")
    private String address;

    @NotBlank(message = "Address couldn't be blank!")
    @Size(min = 10, max = 11)
    private String phone;

    @NotBlank
    private Long gender;
}
