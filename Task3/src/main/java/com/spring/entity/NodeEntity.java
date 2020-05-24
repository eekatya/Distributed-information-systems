package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.org.openstreetmap.osm._0.Node;
import com.spring.org.openstreetmap.osm._0.Tag;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "NODES")
public class NodeEntity {
    @Id
    @Column(name = "id")
    protected Long id;
    @Column(name = "lat")
    protected Double lat;
    @Column(name = "lon")
    protected Double lon;
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
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "nodeEntity", orphanRemoval = true)
    protected List<TagEntity> tags = new ArrayList<>();
    public NodeEntity()
    {}
    public NodeEntity(Long id, Double lat, Double lon, String user, BigInteger uid, BigInteger version, BigInteger changeset, Timestamp timestamp) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.user = user;
        this.uid = uid;
        this.version = version;
        this.changeset = changeset;
        this.timestamp = timestamp;
    }

    public NodeEntity(Node node) {
        this.id = node.getId().longValue();
        this.lat = node.getLat();
        this.lon = node.getLon();
        this.user = node.getUser();
        this.uid = node.getUid();
        this.version = node.getVersion();
        this.changeset = node.getChangeset();
        timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
        List<Tag> tag = node.getTag();
        for (int i = 0; i < tag.size(); i++) {
            Tag element = tag.get(i);
            TagEntity tagEntity = new TagEntity(element.getK(), element.getV(), this);
            tags.add(tagEntity);
        }
    }

    public Long getId() {
      return id;
  }
    public void setId(Long value) {
        this.id = value;
    }
    public Double getLat() {
        return lat;
    }

    public void setLat(Double value) {
        this.lat = value;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double value) {
        this.lon = value;
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
        System.out.println("Id: " + id + " Lat: " + lat + " Lon: " + lon + " User: " + user + " Uid: " + uid + " Version: " + version + " Changeset: " + changeset);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp value) {
        this.timestamp = value;
    }
}

