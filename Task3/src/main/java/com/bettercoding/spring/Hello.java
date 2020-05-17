package com.bettercoding.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Hello {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Hello.class, args);
       // XMlParser parser = new XMlParser();
       // parser.parseXML("RU-NVS.osm");
    }
}



