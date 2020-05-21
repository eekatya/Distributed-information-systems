package com.spring;

import com.spring.entity.*;
import com.spring.org.openstreetmap.osm._0.Way;
import com.spring.service.NodeService;
import com.spring.service.RelationService;
import com.spring.service.UserService;
import com.spring.service.WayService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class Hello {

    @Autowired
    private UserService userService;
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
       // LOGGER.info("LOGGERLOGGERLOGGERLOGGERLOGGERLOGGERLOGGERLOGGERLOGGERLOGGER");
       //System.out.println("SYSTEMSYSTEMSYSTEMSYSTEMSYSTEM");
     /*   NodeEntity nodeEntity1 = new NodeEntity();
        Long id = 35243L;
        Double lat = 84.8766067;
        nodeEntity1.setLat(lat);
        Double lon = 88.8766067;
        nodeEntity1.setLon(lon);
        nodeEntity1.setId(id);
        BigInteger uid = BigInteger.valueOf(777);
        String name_user = "gbcvbvf";
        nodeEntity1.setUid(uid);
        nodeEntity1.setUser(name_user);
        Timestamp tm = new Timestamp(1000);
        nodeEntity1.setTimestamp(tm);
        BigInteger version = BigInteger.valueOf(9);
        nodeEntity1.setVersion(version);
        BigInteger changeset = BigInteger.valueOf(6989);
        nodeEntity1.setChangeset(changeset);
        nodeEntity1.print();
        nodeService.createNodeEntity(nodeEntity1);
        NodeEntity nodeEntity = new NodeEntity();
        id = 35215143L;
        lat = 4.8766067;
        nodeEntity.setLat(lat);
        lon = 8.8766067;
        nodeEntity.setLon(lon);
        nodeEntity.setId(id);
        uid = BigInteger.valueOf(7777);
        name_user = "gbfbf";
        nodeEntity.setUid(uid);
        nodeEntity.setUser(name_user);
        tm = new Timestamp(10000);
        nodeEntity.setTimestamp(tm);
        version = BigInteger.valueOf(6);
        nodeEntity.setVersion(version);
        changeset = BigInteger.valueOf(68989);
        nodeEntity.setChangeset(changeset);
        nodeEntity.print();
        nodeService.createNodeEntity(nodeEntity);
        NodeEntity nodeEntity2 = new NodeEntity();
        id = 35216143L;
        lat = 104.8766067;
        nodeEntity2.setLat(lat);
        lon = 108.8766067;
        nodeEntity2.setLon(lon);
        nodeEntity2.setId(id);
        uid = BigInteger.valueOf(76777);
        name_user = "gb5434f";
        nodeEntity2.setUid(uid);
        nodeEntity2.setUser(name_user);
        tm = new Timestamp(100000);
        nodeEntity2.setTimestamp(tm);
        version = BigInteger.valueOf(6);
        nodeEntity2.setVersion(version);
        changeset = BigInteger.valueOf(68988);
        nodeEntity2.setChangeset(changeset);
        nodeEntity2.print();
        nodeService.createNodeEntity(nodeEntity2);*/
      /*  Address address = new Address();
        address.setCity("Kiev");
        address.setHomeNumber("4");
        address.setStreet("Main Street");
        Address address1 = new Address();
        address1.setCity("Lviv");
        Users users = new Users();
        users.setAddress(address);
        users.setEmail("someEmail@gmail.com");
        users.setName("Smith");
        userService.createUsers(users);
        Users users1 = new Users();
        users1.setName("Jon Dorian");
        users1.setEmail("gmailEmail@gmail.com");
        users1.setAddress(address1);
        userService.createUsers(users1);*/
       /* WayEntity wayEntity = new WayEntity();
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
        wayService.createWayEntity(wayEntity);*/
      /*  RelationEntity relationEntity = new RelationEntity();
        Long id = 3521065143L;
        relationEntity.setId(id);
        BigInteger uid = BigInteger.valueOf(777777);
        String name_user = "User_name";
        relationEntity.setUid(uid);
        relationEntity.setUser(name_user);
        Timestamp tm = new Timestamp(10000);
        relationEntity.setTimestamp(tm);
        BigInteger version = BigInteger.valueOf(6);
        relationEntity.setVersion(version);
        BigInteger changeset = BigInteger.valueOf(68989);
        relationEntity.setChangeset(changeset);
        relationEntity.print();
        relationService.createRelationEntity(relationEntity);*/

        userService.findAll().forEach(Users::print);

       // userService.findAllByName("Smith").forEach(it-> System.out.println(it));
        //userService.findWhereEmailIsGmail().forEach(it-> System.out.println(it));

        //userService.findWhereNameStartsFromSmith().forEach(it-> System.out.println(it));
    }
}



