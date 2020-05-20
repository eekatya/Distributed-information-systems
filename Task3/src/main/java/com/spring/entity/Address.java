package com.spring.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "home_number")
    private String homeNumber;

    public void setCity(String kiev) {
        city = kiev;
    }

    public void setHomeNumber(String s) {
        homeNumber = s;
    }

    public void setStreet(String main_street) {
        street = main_street;
    }

//getters and setters
}