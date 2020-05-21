package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.org.openstreetmap.osm._0.Tag;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "NODES1")
public class NodeEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
   // private long nodeId;
   // protected List<Tag> tag;
    @Column(name = "id")
    protected Long id;
   // protected BigInteger id;
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
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "node_id")
   // @OneToMany(targetEntity=TagEntity.class, mappedBy="NODES!",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    //protected List<Tag> tag;
  /*  @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nodeEntity", orphanRemoval = true)
    private List<TagEntity> tags = new ArrayList<>();*/
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
  /*  public List<TagEntity> getTags() {
        return tags;
    }
    public void addTag(TagEntity tagEntity) {
        if (tags.contains(tagEntity)) {
            return;
        }
        tags.add(tagEntity);
        tagEntity.setNodeEntity(this);
    }*/

}

