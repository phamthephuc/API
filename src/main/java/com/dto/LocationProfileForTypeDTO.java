package com.dto;

import com.entity.Picture;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class LocationProfileForTypeDTO {
    Long id;
    String name;
    String introduction;
    List<Picture> pictureList;

    private Long sumRating;
    private int numRating;

    public int getNumRating() {
        return numRating;
    }

    public void setNumRating(int numRating) {
        this.numRating = numRating;
    }

    public void setSumRating(Long crrRating) {
        this.sumRating = crrRating;
    }

    public Long getSumRating() {
        return sumRating;
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

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }


}
