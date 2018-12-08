package com.dto;

import lombok.Data;

@Data

public class UsersProfileResponse {

    String username;
    String address;
    String phone;
    Long gender;
    String fullname;

    public UsersProfileResponse() {
    }

    public UsersProfileResponse(String username, String address, String phone, Long gender, String fullname) {
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
