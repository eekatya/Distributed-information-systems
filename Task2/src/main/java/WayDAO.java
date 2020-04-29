import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Way;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.sql.*;
import java.util.GregorianCalendar;

public class WayDAO {
    private postgreDatabase database;
    private Connection conn;

    WayDAO(Connection conn) {
        this.conn = conn;
    }
    public void insertStatement(Way way) {
        Statement stmt;
        String sql;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(way.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "INSERT INTO WAYS (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET) VALUES (" + way.getId() + ", " + way.getVersion() + ", " + "'" + timestamp + "'" + ", " + way.getUid() + ", " + "'" + way.getUser().replace('\'', '.') + "'" + ", " + way.getChangeset() + ");";
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

    public Way getById(int id) {
        Way way = new Way();
        try {
            PreparedStatement pStatement =
                    conn.prepareStatement("SELECT * FROM WAYS WHERE id=?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                way.setId(new BigInteger(Integer.valueOf(rs.getInt(1)).toString()));
                way.setVersion(new BigInteger(Integer.valueOf(rs.getInt(2)).toString()));
                Timestamp timestamp = rs.getTimestamp(3);
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(timestamp.getTime());
                way.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                way.setUid(new BigInteger(Integer.valueOf(rs.getInt(4)).toString()));
                way.setUser(rs.getString(5));
                way.setChangeset(new BigInteger(Integer.valueOf(rs.getInt(6)).toString()));
            }
        } catch (SQLException | DatatypeConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return way;

    }


    public  void update(Way way) {
        Statement stmt;
        String sql;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(way.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "UPDATE WAYS SET ID = " + way.getId() + "," + " VERSION = " + way.getVersion() + ", " + " _TIMESTAMP = " + "'" + timestamp + "'" + ", " + " UID = " + way.getUid() + ", " + " USER_NAME = " + "'" + way.getUser().replace('\'', '.') + "'" + ", " + " CHANGESET = " + way.getChangeset() + " WHERE ID = " + way.getId() + ";";
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




    public  void delete(int id)
    {
        try {
            PreparedStatement pStatement = conn.prepareStatement(
                    "DELETE FROM WAYS WHERE ID = ?");
            pStatement.setObject(1, id);
            pStatement.executeUpdate();
            conn.commit();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
