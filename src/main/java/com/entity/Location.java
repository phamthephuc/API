package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String introduction;
    Date createdDate;
    Long idPlaceCategory;
    Long idContent;
    Long idStatus;
    Long idAddress;
    Long idContact;
    Long idUser;
    Long idDuration;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getIdPlaceCategory() {
        return idPlaceCategory;
    }

    public void setIdPlaceCategory(Long idPlaceCategory) {
        this.idPlaceCategory = idPlaceCategory;
    }

    public Long getIdContent() {
        return idContent;
    }

    public void setIdContent(Long idContent) {
        this.idContent = idContent;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdDuration() {
        return idDuration;
    }

    public void setIdDuration(Long idDuration) {
        this.idDuration = idDuration;
    }
}
