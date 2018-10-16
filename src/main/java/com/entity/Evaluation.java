package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Evaluation {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    Long score;
    Long idLocation;
    Long idUser;
}
