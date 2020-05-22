package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;
@Entity
@Table(name = "WAYS")
public class WayEntity {
    @Id
    @Column(name = "id")
    protected Long id;
    @Column(name = "user_name")
    protected String user;
    @Column(name = "uid")
    protected BigInteger uid;
    @Column(name = "version")
    protected BigInteger version;
    @Column(name = "changeset")
    protected BigInteger changeset;
    @Column(name = "_timestamp")
    protected Timestamp timestamp;
    public WayEntity()
    {}
    public WayEntity(Long id, String user, BigInteger uid, BigInteger version, BigInteger changeset, Timestamp timestamp) {
        this.id = id;
        this.user = user;
        this.uid = uid;
        this.version = version;
        this.changeset = changeset;
        this.timestamp = timestamp;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long value) {
        this.id = value;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String value) {
        this.user = value;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger value) {
        this.uid = value;
    }


    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger value) {
        this.version = value;
    }

    public BigInteger getChangeset() {
        return changeset;
    }

    public void setChangeset(BigInteger value) {
        this.changeset = value;
    }
    public void print()
    {
        System.out.println("Id: " + id + " User: " + user + " Uid: " + uid + " Version: " + version + " Changeset: " + changeset);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp value) {
        this.timestamp = value;
    }
}


