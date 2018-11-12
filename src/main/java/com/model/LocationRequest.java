package com.model;


import lombok.Data;

@Data

public class LocationRequest {

    String name;
    String introduction;
    Long idPlaceType;
    Long idPlaceCategory;
    String content;
    Long idStatus;
    Double longitudeAddress;
    Double latitudeAddress;
    String nameAddress;
    String phone;
    String email;
    Long idDuration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getIdPlaceType() {
        return idPlaceType;
    }

    public void setIdPlaceType(Long idPlaceType) {
        this.idPlaceType = idPlaceType;
    }

    public Long getIdPlaceCategory() {
        return idPlaceCategory;
    }

    public void setIdPlaceCategory(Long idPlaceCategory) {
        this.idPlaceCategory = idPlaceCategory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Double getLongitudeAddress() {
        return longitudeAddress;
    }

    public void setLongitudeAddress(Double longitudeAddress) {
        this.longitudeAddress = longitudeAddress;
    }

    public Double getLatitudeAddress() {
        return latitudeAddress;
    }

    public void setLatitudeAddress(Double latitudeAddress) {
        this.latitudeAddress = latitudeAddress;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdDuration() {
        return idDuration;
    }

    public void setIdDuration(Long idDuration) {
        this.idDuration = idDuration;
    }
}
