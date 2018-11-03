package com.dto;

import com.entity.Picture;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

public class LocationProfileForTypeDTO {
    Long id;
    String name;
    long numRating;
    BigDecimal sumRating;

    List<Picture> pictureList;

    public long getNumRating() {
        return numRating;
    }

    public void setNumRating(long numRating) {
        this.numRating = numRating;
    }

    public void setSumRating(BigDecimal crrRating) {
        this.sumRating = crrRating;
    }

    public BigDecimal getSumRating() {
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

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }


}
