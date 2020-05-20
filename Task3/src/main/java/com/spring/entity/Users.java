package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column
    @JsonProperty("name")
    private String name;

    @Column
    @JsonProperty("email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String s) {
        email = s;
    }

    public void setName(String smith) {
        name = smith;
    }
    public void print()
    {
        System.out.println("Name: " + name + " email: " + email);
    }
}
//getters and setters