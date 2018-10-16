package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Favorite {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    Long idLocation;
    Long idUser;
}
