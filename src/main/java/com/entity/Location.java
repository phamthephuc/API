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

}
