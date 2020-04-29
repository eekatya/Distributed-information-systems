import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;

public class postgreDatabase {
    private static Logger LOGGER = LoggerFactory.getLogger(postgreDatabase.class);
    public static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DB_USER_NAME = "postgres";
  //  public static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/node_database" ;
    public static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/___node_database" ;
    public static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/";
    public static final String DB_PASSWORD = "1";
  //  public static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/";
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
        sql = "DROP DATABASE IF EXISTS ___node_database";
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
        sql = "CREATE DATABASE ___node_database";
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

    }

    public Connection getConnection() {
        return conn;
    }
}

/*    private void createAllTables() throws Exception {
        Statement stmt;
        String sql;
        Class.forName(DB_DRIVER_NAME);
        conn = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
        System.err.println("Connected to database successfully");
        conn.setAutoCommit(false);

        stmt = conn.createStatement();
        sql = "CREATE TABLE MANAGERS " + // Таблица 1. "Менеджеры"
                "(ID INT PRIMARY KEY    NOT NULL," +
                " LNAME VARCHAR(50)     NOT NULL," +
                " FNAME VARCHAR(50)     NOT NULL," +
                " MNAME VARCHAR(50)," +
                " PASSPORT VARCHAR(100) NOT NULL," +
                " HIRE DATE             NOT NULL," +
                " DISMISS DATE" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table MANAGERS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE SPARES " + // Таблица 2. "Детали"
                "(ID INT PRIMARY KEY  NOT NULL," +
                " NAME VARCHAR(100)   NOT NULL," +
                " ARTICLE VARCHAR(50) NOT NULL" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table SPARES completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE ORDER_STATUSES " + // Таблица 4. "Статусы заказа"
                "(ID INT PRIMARY KEY NOT NULL," +
                " NAME VARCHAR(50)   NOT NULL" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table ORDER_STATUSES completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE BUYER_TYPES" + // Таблица "Типы покупателей"
                " (ID INT PRIMARY KEY NOT NULL," +
                " NAME VARCHAR(50)    NOT NULL" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table BUYER_TYPES completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE BUYERS " + // Таблица 5. "Покупатели"
                "(ID INT PRIMARY KEY   NOT NULL," +
                " BUYER_ID INT         NOT NULL," +
                " TYPE INT             NOT NULL," +
                " PHONE CHAR(12)       NOT NULL," +
                " EMAIL VARCHAR(40)    NOT NULL," +
                " ADDRESS VARCHAR(100) NOT NULL," +
                " FOREIGN KEY (TYPE) REFERENCES BUYER_TYPES (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table BUYERS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE PRIVATE_BUYERS" + // Таблица "Покупатели физ. лица"
                " (ID INT PRIMARY KEY       NOT NULL," +
                " LNAME VARCHAR(50)         NOT NULL," +
                " FNAME VARCHAR(50)         NOT NULL," +
                " MNAME VARCHAR(50)," +
                " PASSPORT VARCHAR(100)     NOT NULL," +
                " REGISTRATION VARCHAR(100)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table PRIVATE_BUYERS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE LEGAL_BUYERS" + // Таблица "Покупатели юр. лица"
                " (ID INT PRIMARY KEY  NOT NULL," +
                " NAME VARCHAR(80)     NOT NULL," +
                " ADDRESS VARCHAR(100) NOT NULL," +
                " INN CHAR(12)         NOT NULL," +
                " CONTACT VARCHAR(80)  NOT NULL," +
                " BANK_DETAILS VARCHAR(150)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table LEGAL_BUYERS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE SUPPLIER_TYPES" + // Таблица "Типы поставщиков"
                " (ID INT PRIMARY KEY NOT NULL," +
                " NAME VARCHAR(50)    NOT NULL" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table SUPPLIER_TYPES completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE SUPPLIERS" + // Таблица "Поставщики"
                " (ID INT PRIMARY KEY       NOT NULL," +
                " TYPE INT                  NOT NULL," +
                " NAME VARCHAR(80)          NOT NULL," +
                " ADDRESS VARCHAR(100)      NOT NULL," +
                " INN CHAR(12)              NOT NULL," +
                " CONTACT VARCHAR(80)       NOT NULL," +
                " BANK_DETAILS VARCHAR(150)," +
                " STATUS BOOLEAN            NOT NULL," +
                " FOREIGN KEY (TYPE) REFERENCES SUPPLIER_TYPES (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table SUPPLIERS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE SUPPLIER_SPARE" + // Таблица "Запчасти поставляемые поставщиками"
                " (ID INT PRIMARY KEY NOT NULL," +
                " SPARE_ID INT        NOT NULL," +
                " SUPPLIER_ID INT     NOT NULL," +
                " TIME INT            NOT NULL," +
                " COST MONEY          NOT NULL," +
                " FOREIGN KEY (SPARE_ID) REFERENCES SPARES (ID)," +
                " FOREIGN KEY (SUPPLIER_ID) REFERENCES SUPPLIERS (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table SUPPLIER_SPARE completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE ORDERS " + // Таблица 3. "Заказы"
                "(ID INT PRIMARY KEY NOT NULL," +
                " NUMBER VARCHAR(20) NOT NULL," +
                " ORDER_DATE DATE    NOT NULL," +
                " RECEIVE_DATE DATE CONSTRAINT rcv_date_less_ord_date CHECK (RECEIVE_DATE = NULL OR RECEIVE_DATE >= ORDER_DATE)," +
                " STATUS INT         NOT NULL," +
                " SUPPLIER INT       NOT NULL," +
                " WARRANTY BOOLEAN   NOT NULL," +
                " MANAGER INT        NOT NULL," +
                " FOREIGN KEY (STATUS) REFERENCES ORDER_STATUSES (ID)," +
                " FOREIGN KEY (SUPPLIER) REFERENCES SUPPLIERS (ID)," +
                " FOREIGN KEY (MANAGER) REFERENCES MANAGERS (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table BUYERS completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE ORDERS_STRUCTURE " + // Таблица 9. "Состав заказов"
                "(ID INT PRIMARY KEY NOT NULL," +
                " ORDER_ID INT NOT NULL," +
                " SPARE_ID INT NOT NULL," +
                " SPARES_NUM INT," +
                " COST MONEY         NOT NULL," +
                " FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ID)," +
                " FOREIGN KEY (SPARE_ID) REFERENCES SPARES (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table ORDERS_STRUCTURE completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE WAREHOUSE_STRUCTURE " + // Таблица 17. "Состав склада"
                "(ID INT PRIMARY KEY NOT NULL," +
                " PLACE INT          NOT NULL CHECK (PLACE > 0)," +
                " ORDER_ID INT       NOT NULL," +
                " ACTIVE BOOLEAN     NOT NULL," +
                " FOREIGN KEY (ORDER_ID) REFERENCES ORDERS_STRUCTURE (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table WAREHOUSE_STRUCTURE completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE SALES " + // Таблица 18. "Продажи"
                "(ID INT PRIMARY KEY NOT NULL," +
                " BUYER_ID INT       NOT NULL," +
                " SALE_DATE DATE     NOT NULL," +
                " FOREIGN KEY (BUYER_ID) REFERENCES BUYERS (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table SALES completed successfully");

        stmt = conn.createStatement();
        sql = "CREATE TABLE WAREHOUSE_SALES " + // Таблица 19. "Продажи со склада"
                "(ID INT PRIMARY KEY NOT NULL," +
                " WAREHOUSE_ID INT   NOT NULL," +
                " SALE_ID INT        NOT NULL," +
                " COST MONEY         NOT NULL," +
                " FOREIGN KEY (WAREHOUSE_ID) REFERENCES WAREHOUSE_STRUCTURE (ID)," +
                " FOREIGN KEY (SALE_ID) REFERENCES SALES (ID)" +
                ")";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.commit();
        System.err.println("Create table WAREHOUSE_SALES completed successfully");
        // TODO: complete other tables
    }*/
