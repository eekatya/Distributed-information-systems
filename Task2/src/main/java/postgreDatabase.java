import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class postgreDatabase {
    private static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private static final String DB_USER_NAME = "postgres";
    private static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432//node_database" ;
    private static final String DB_PASSWORD = "1";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/";
    private Connection conn;

    public postgreDatabase()
    {
        connectDatabase();
    }
    public void connectDatabase() {
        try {
            Class.forName(DB_DRIVER_NAME);
            conn = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
            System.err.println("Connected to database successfully");
        } catch (Exception e) {
            System.err.println("Exception in connection to database");
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return;
        }
    }
    public void disconnectDatabase() {
        try {
            conn.close();
            conn = null;
            System.err.println("Disconnected from database successfully");
        } catch (Exception e) {
            System.err.println("Exception in disconnection from database");
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return;
        }
    }
    private void dropDatabase() throws Exception {
        Statement stmt;
        String sql;
        Class.forName(DB_DRIVER_NAME);
        conn = DriverManager.getConnection(CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
        System.err.println("Connected to postgreSQL successfully");
        conn.setAutoCommit(true);
        stmt = conn.createStatement();
        sql = "DROP DATABASE IF EXISTS spare_shop";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
        conn = null;
        System.err.println("Drop database completed successfully");
    }

    private void createDatabase() throws Exception {
        Statement stmt;
        String sql;
        Class.forName(DB_DRIVER_NAME);
        conn = DriverManager.getConnection(CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
        System.err.println("Connected to postgreSQL successfully");
        conn.setAutoCommit(true);
        stmt = conn.createStatement();
        sql = "CREATE DATABASE spare_shop";
        stmt.execute(sql);
        stmt.close();
        conn.close();
        conn = null;
        System.err.println("Create database completed successfully");
    }
}
