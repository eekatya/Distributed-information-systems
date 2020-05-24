package com.spring.entity;
import javax.persistence.*;
@Entity
@Table(name = "TAGS")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tags_id")
    protected Long id;
    @Column(name = "k")
    protected String k;
    @Column(name = "v")
    protected String v;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private NodeEntity nodeEntity;
    public TagEntity()
    {

    }
    public TagEntity(String k, String v, NodeEntity nodeEntity) {
        this.k = k;
        this.v = v;
        this.nodeEntity = nodeEntity;
    }
    public String getK() {
        return k;
    }
    public void setK(String value) {
        this.k = value;
    }
    public String getV() {
        return v;
    }
    public void setV(String value) {
        this.v = value;
    }
}
