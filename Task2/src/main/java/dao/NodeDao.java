package dao;
import org.openstreetmap.osm._0.Node;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;


public class NodeDao extends DAO<Node> {
    private Connection conn;
    private SpeedCalculation calc;

    public NodeDao(Connection conn) {
        this.conn = conn;
        calc = new SpeedCalculation();
    }

    @Override
    public void insertBatch(ArrayList<Node> list) {
        String query = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pStatement = null;
        Timestamp timestamp = null;
        try {
            pStatement = conn.prepareStatement(query);
            for (Node node : list) {
                timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
                pStatement.setBigDecimal(1, new BigDecimal(node.getId()));
                pStatement.setBigDecimal(2, new BigDecimal(node.getVersion()));
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
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insertPreparedStatement(Node node) {
        String query = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pStatement = null;
        Timestamp timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
        try {
            pStatement = conn.prepareStatement(query);
            pStatement.setBigDecimal(1, new BigDecimal(node.getId()));
            pStatement.setBigDecimal(2, new BigDecimal(node.getVersion()));
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
            pStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insertStatement(Node node) {
        Statement stmt;
        String sql;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (" + node.getId() + ", " + node.getVersion() + ", " + "'" + timestamp + "'" + ", " + node.getUid() + ", " + "'" + node.getUser().replace('\'', '.') + "'" + ", " + node.getChangeset() + ", " + node.getLat().toString().replace(',', '.') + ", " + node.getLon().toString().replace(',', '.') + ");";
            long startTime = System.currentTimeMillis();
            stmt.executeUpdate(sql);
            conn.commit();
            long finishTime = System.currentTimeMillis();
            calc.setConsumedMillis(finishTime - startTime);
            calc.addRecords(1);
            System.out.println("Затрачено " + calc.getTime() + " миллисекунд; Столько записей:" + calc.getNumOfRecords());
            stmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public long getInsertTime() {
        return calc.timeCalculate();
    }
    @Override
    public Node getById(int id) {
        Node node = new Node();
        try  {
            PreparedStatement pStatement =
                    conn.prepareStatement("SELECT * FROM NODES WHERE id=?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                node.setId(new BigInteger(Integer.valueOf(rs.getInt(1)).toString()));
                node.setVersion(new BigInteger(Integer.valueOf(rs.getInt(2)).toString()));
                Timestamp timestamp = rs.getTimestamp(3);
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(timestamp.getTime());
                node.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                node.setUid(new BigInteger(Integer.valueOf(rs.getInt(4)).toString()));
                node.setUser(rs.getString(5));
                node.setChangeset(new BigInteger(Integer.valueOf(rs.getInt(6)).toString()));
                node.setLat(rs.getDouble(7));
                node.setLon(rs.getDouble(8));
            }
        } catch (SQLException  | DatatypeConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return node;

    }
    @Override
    public  void update(Node node)
    {
        Statement stmt;
        String sql;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "UPDATE NODES SET ID = " +  node.getId() + "," + " VERSION = " + node.getVersion() + ", " + " _TIMESTAMP = " + "'" + timestamp + "'" + ", " + " UID = " + node.getUid() + ", " + " USER_NAME = "  + "'" + node.getUser().replace('\'', '.') + "'" + ", " + " CHANGESET = " + node.getChangeset() + ", " + " LAT = " + node.getLat().toString().replace(',', '.') + ", " + " LON = " + node.getLon().toString().replace(',', '.') +" WHERE ID = " + node.getId() + ";";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            conn.commit();
            stmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public  void delete(int id)
    {
        try {
            PreparedStatement pStatement = conn.prepareStatement(
                    "DELETE FROM NODES WHERE id = ?");
            pStatement.setObject(1, id);
            pStatement.executeUpdate();
            conn.commit();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
