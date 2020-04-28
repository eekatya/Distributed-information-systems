import org.openstreetmap.osm._0.Node;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class NodeDao extends DAO<Node> {
    private postgreDatabase database;
    private Connection conn;
    private SpeedCalculation calc;

    NodeDao(Connection conn)
    {
        this.conn = conn;
        calc = new SpeedCalculation();
    }

    public void insertBatch(ArrayList<Node> list) {
        String query = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pStatement = null;
        try {
            pStatement = conn.prepareStatement(query);
            for (Node node : list) {
                pStatement.setBigDecimal(1, new BigDecimal(node.getId()));
                pStatement.setBigDecimal(2, new BigDecimal(node.getVersion()));
                Timestamp timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
                pStatement.setTimestamp(3, timestamp);
                pStatement.setBigDecimal(4, new BigDecimal(node.getUid()));
                pStatement.setString(5, node.getUser());
                pStatement.setBigDecimal(6, new BigDecimal(node.getChangeset()));
                pStatement.setDouble(7, node.getLat());
                pStatement.setDouble(8, node.getLon());
                pStatement.addBatch();
            }
            long startTime = System.currentTimeMillis();
            pStatement.executeBatch();
            conn.commit();
            long finishTime = System.currentTimeMillis();
            calc.setConsumedMillis(finishTime - startTime);
            calc.addRecords(list.size());
             System.out.println("Затрачено " + calc.getTime() + " миллисекунд; Столько записей:" + calc.getNumOfRecords());
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPreparedStatement(Node node) {
        String query = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pStatement = null;
        try {
            pStatement = conn.prepareStatement(query);
            pStatement.setBigDecimal(1, new BigDecimal(node.getId()));
            pStatement.setBigDecimal(2, new BigDecimal(node.getVersion()));
            Timestamp timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
            pStatement.setTimestamp(3, timestamp);
            pStatement.setBigDecimal(4, new BigDecimal(node.getUid()));
            pStatement.setString(5, node.getUser());
            pStatement.setBigDecimal(6, new BigDecimal(node.getChangeset()));
            pStatement.setDouble(7, node.getLat());
            pStatement.setDouble(8, node.getLon());
            long startTime = System.currentTimeMillis();
            pStatement.executeUpdate();
            conn.commit();
            long finishTime = System.currentTimeMillis();
            calc.setConsumedMillis(finishTime - startTime);
            calc.addRecords(1);
           // System.out.println("Затрачено " + calc.getTime() + " миллисекунд; Столько записей:" + calc.getNumOfRecords());
            pStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStatement(Node node) {
        Statement stmt;
        String sql;
        Timestamp timestamp = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (" + node.getId().toString() + ", " + node.getVersion().toString()+ ", "  +  "'" + timestamp.toString().replace(',', '.') + "'"  + ", " + node.getUid().toString()+ ", " +  "'" + node.getUser().replace('\'', '.')+ "'" + ", " + node.getChangeset().toString()+ ", " + node.getLat().toString().replace(',', '.')+ ", " + node.getLon().toString().replace(',', '.') + ");";
            long startTime = System.currentTimeMillis();
            stmt.executeUpdate(sql);
            conn.commit();
            long finishTime = System.currentTimeMillis();
            calc.setConsumedMillis(finishTime - startTime);
            calc.addRecords(1);
            System.out.println("Затрачено " + calc.getTime() + " миллисекунд; Столько записей:" + calc.getNumOfRecords());
            stmt.close();
            //System.err.println("Table NODES filled");
        } catch (Exception e) {
            try {
                //System.err.println("INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (" + node.getId().toString() + ", " + node.getVersion().toString()+ ", "  +  "'" + timestamp.toString().replace(',', '.') + "'"  + ", " + node.getUid().toString()+ ", " +  "'" + node.getUser().replace('\'', '.')+ "'" + ", " + node.getChangeset().toString()+ ", " + node.getLat().toString().replace(',', '.')+ ", " + node.getLon().toString().replace(',', '.') + ");");
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public long getInsertTime()
    {
        return calc.timeCalculate();
    }
    public  Node getById(int id)
    {
        Node node = null;
        return node;
    }
    public  void update(Node adr)
    {

    }
    public  void delete(Node adr)
    {

    }
    public  ArrayList<Node> getAll()
    {
        ArrayList<Node> list = new ArrayList<>();
        return list;
    }
}
