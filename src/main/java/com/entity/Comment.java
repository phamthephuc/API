package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data

public class Comment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    Date commentedDate;
    Long idLocation;
    Long idUser;
}
