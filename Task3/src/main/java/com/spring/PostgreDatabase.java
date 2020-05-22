package com.spring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreDatabase {
    private static Logger LOGGER = LoggerFactory.getLogger(PostgreDatabase.class);
    public static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DB_USER_NAME = "postgres";
    public static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/nodedatabase" ;
    public static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/";
    public static final String DB_PASSWORD = "1";
    private Connection conn;
    public void connectDatabase() {
        try {
            Class.forName(DB_DRIVER_NAME);
            conn = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
            LOGGER.info("Connected to database successfully");
        } catch (Exception e) {
            LOGGER.error("Exception in connection to database");
            e.printStackTrace();
            LOGGER.error(e.getClass().getName() + ": " + e.getMessage());
            return;
        }
    }
    public void disconnectDatabase() {
        try {
            conn.close();
            conn = null;
            LOGGER.info("Disconnected from database successfully");
        } catch (Exception e) {
            LOGGER.error("Exception in disconnection from database");
            e.printStackTrace();
            LOGGER.error(e.getClass().getName() + ": " + e.getMessage());
            return;
        }
    }
    public void dropDatabase() throws Exception {
        Statement stmt;
        String sql;
        Class.forName(DB_DRIVER_NAME);
        conn = DriverManager.getConnection(CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
        LOGGER.info("Connected to postgreSQL successfully");
        conn.setAutoCommit(true);
        stmt = conn.createStatement();
        sql = "DROP DATABASE IF EXISTS nodedatabase";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
        conn = null;
        LOGGER.info("Drop database completed successfully");
    }

    public void createDatabase() throws Exception {
        Statement stmt;
        String sql;
        Class.forName(DB_DRIVER_NAME);
        conn = DriverManager.getConnection(CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
        LOGGER.info("Connected to postgreSQL successfully");
        conn.setAutoCommit(true);
        stmt = conn.createStatement();
        sql = "CREATE DATABASE nodedatabase";
        stmt.execute(sql);
        stmt.close();
        conn.close();
        conn = null;
        LOGGER.info("Create database completed successfully");
    }
    public void createAllTables() throws Exception {
        Statement stmt;
        String sql;
        Class.forName(DB_DRIVER_NAME);
        conn = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
        LOGGER.info("Connected to database successfully");
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        sql = "CREATE TABLE NODES " + // Таблица 1. "Nodes"
                "(ID BIGINT PRIMARY KEY NOT NULL," +
                " VERSION BIGINT," +
                " _TIMESTAMP TIMESTAMPTZ," +
                " UID BIGINT," +
                " USER_NAME TEXT," +
                " CHANGESET BIGINT," +
                " LAT DOUBLE PRECISION," +
                " LON DOUBLE PRECISION" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        LOGGER.info("Create table NODES completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE WAYS " + // Таблица 2. "Way"
                "(ID BIGINT PRIMARY KEY NOT NULL," +
                " VERSION BIGINT," +
                " _TIMESTAMP TIMESTAMPTZ," +
                " UID BIGINT," +
                " USER_NAME TEXT," +
                " CHANGESET BIGINT" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        LOGGER.info("Create table WAYS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE RELATIONS " + // Таблица 3. "Relations"
                "(ID BIGINT PRIMARY KEY NOT NULL," +
                " VERSION BIGINT," +
                " _TIMESTAMP TIMESTAMPTZ," +
                " UID BIGINT," +
                " USER_NAME TEXT," +
                " CHANGESET BIGINT" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        LOGGER.info("Create table RELATIONS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE TAGS " + // Таблица 4. "Tags"
                "(TAGS_ID SERIAL PRIMARY KEY," +
                " K TEXT," +
                " V TEXT," +
                " ID BIGINT NOT NULL REFERENCES NODES (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        LOGGER.info("Create table TAGS completed successfully");
    }

    public Connection getConnection() {
        return conn;
    }
}
