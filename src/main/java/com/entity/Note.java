package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Note {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String Content;
    Long idLocation;
    Long idUser;
}
