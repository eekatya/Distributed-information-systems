package com.spring.entity;
import javax.persistence.*;
@Entity
@Table(name = "TAGS")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "k")
    protected String k;
    @Column(name = "v")
    protected String v;
   // @Column(name = "node_id")
   // protected Long id;
   // @ManyToOne()
   // @JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
   // protected NodeEntity nodeEntity;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private NodeEntity nodeEntity;
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

    public void setNodeEntity(NodeEntity nodeEntity) {
       /*     if (sameAsFormer(nodeEntity)) {
                return;
            }

            //set new nodeEntity
            NodeEntity oldNodeEntity = this.nodeEntity;
            this.nodeEntity = nodeEntity;
            //remove from the old node entity
            if (oldNodeEntity != null)
                oldNodeEntity.removeTag(this);
            //set myself into new node entity
            if (nodeEntity != null)
                nodeEntity.addTag(this);*/

    }
 /*   public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }*/
}
