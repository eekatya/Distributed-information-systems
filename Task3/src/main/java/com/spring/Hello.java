package com.spring;

import com.spring.entity.NodeEntity;
import com.spring.entity.RelationEntity;
import com.spring.entity.WayEntity;
import com.spring.service.NodeService;
import com.spring.service.RelationService;
import com.spring.service.WayService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigInteger;
import java.sql.Timestamp;

@SpringBootApplication
public class Hello {

    @Autowired
    private NodeService nodeService;
    @Autowired
    private WayService wayService;
    @Autowired
    private RelationService relationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);
    public static void main(String[] args) {
        SpringApplication.run(Hello.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods(){
      /*  WayEntity wayEntity = new WayEntity();
        Long id = 3521065143L;
        wayEntity.setId(id);
        BigInteger uid = BigInteger.valueOf(777777);
        String name_user = "User_name";
        wayEntity.setUid(uid);
        wayEntity.setUser(name_user);
        Timestamp tm = new Timestamp(10000);
        wayEntity.setTimestamp(tm);
        BigInteger version = BigInteger.valueOf(6);
        wayEntity.setVersion(version);
        BigInteger changeset = BigInteger.valueOf(68989);
        wayEntity.setChangeset(changeset);
        wayEntity.print();
        wayService.createWayEntity(wayEntity);
        RelationEntity relationEntity = new RelationEntity();
        Long id1 = 3521043L;
        relationEntity.setId(id1);
        BigInteger uid1 = BigInteger.valueOf(77777);
        String name_user1 = "User_name";
        relationEntity.setUid(uid1);
        relationEntity.setUser(name_user1);
        Timestamp tm1 = new Timestamp(100000);
        relationEntity.setTimestamp(tm1);
        BigInteger version1 = BigInteger.valueOf(8);
        relationEntity.setVersion(version1);
        BigInteger changeset1 = BigInteger.valueOf(689);
        relationEntity.setChangeset(changeset1);
        relationEntity.print();
        relationService.createRelationEntity(relationEntity);*/
    }

}



