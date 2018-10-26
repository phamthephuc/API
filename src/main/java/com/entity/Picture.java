package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Picture {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String image;
    Long idLocation;


    public  Picture(long id, String name , String link, Long idLocation){
        this.id = id;
        this.name = name;
        this.image = link;
        this.idLocation = idLocation;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }
}
