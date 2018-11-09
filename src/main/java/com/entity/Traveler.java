package com.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class Traveler {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;

    Long roleId;



}
