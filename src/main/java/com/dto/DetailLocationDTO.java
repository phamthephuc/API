package com.dto;

import com.entity.Picture;

import java.math.BigDecimal;
import java.util.List;


public class DetailLocationDTO {

    Long id;
    String name;
    String introduction;
    String content;
    String address;
    Double latitude;
    Double longitude;
    String phone;
    String email;

    List<Picture> pictureList;
    boolean isFavorite;
    BigDecimal sumRating;
    long numRating;
    EvaluationPaginationDTO evaluationPaginationDTO;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public EvaluationPaginationDTO getEvaluationPaginationDTO() {
        return evaluationPaginationDTO;
    }

    public void setEvaluationPaginationDTO(EvaluationPaginationDTO evaluationPaginationDTO) {
        this.evaluationPaginationDTO = evaluationPaginationDTO;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public void setNumRating(long numRating) {
        this.numRating = numRating;
    }

    public void setSumRating(BigDecimal sumRating) {
        this.sumRating = sumRating;
    }

    public BigDecimal getSumRating() {
        return sumRating;
    }

    public long getNumRating() {
        return numRating;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

}
