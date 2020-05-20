package com.spring.entity;
import javax.persistence.*;
@Entity
@Table(name = "TAGS")
public class TagEntity {
    @Id
    @Column(name = "k")
    protected String k;
    @Column(name = "v")
    protected String v;
}
