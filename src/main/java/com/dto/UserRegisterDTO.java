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

    private String fullname;

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

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO(String username, String fullname, String password, String address,  String phone, Long gender) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }
}
