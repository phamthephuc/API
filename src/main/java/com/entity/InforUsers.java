package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class InforUsers {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private String address;
    private String phone;
    private Long gender;
    private String fullname;
}
