package dao;
import org.openstreetmap.osm._0.Relation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RelationDAO extends DAO<Relation> {
    private static Logger LOGGER = LoggerFactory.getLogger(RelationDAO.class);
    private Connection conn;

    public RelationDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insertBatch(ArrayList<Relation> list) {
        LOGGER.info("Not yet implemented");
    }

    @Override
    public void insertPreparedStatement(Relation rel) {
        LOGGER.info("Not yet implemented");
    }

    @Override
    public void insertStatement(Relation rel) {
        Statement stmt;
        String sql;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(rel.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "INSERT INTO RELATIONS (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET) VALUES (" + rel.getId() + ", " + rel.getVersion() + ", " + "'" + timestamp + "'" + ", " + rel.getUid() + ", " + "'" + rel.getUser().replace('\'', '.') + "'" + ", " + rel.getChangeset() + ");";
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
    public Relation getById(int id) {
        Relation rel = new Relation();
        try {
            PreparedStatement pStatement =
                    conn.prepareStatement("SELECT * FROM RELATIONS WHERE id=?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                rel.setId(new BigInteger(Integer.valueOf(rs.getInt(1)).toString()));
                rel.setVersion(new BigInteger(Integer.valueOf(rs.getInt(2)).toString()));
                Timestamp timestamp = rs.getTimestamp(3);
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(timestamp.getTime());
                rel.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                rel.setUid(new BigInteger(Integer.valueOf(rs.getInt(4)).toString()));
                rel.setUser(rs.getString(5));
                rel.setChangeset(new BigInteger(Integer.valueOf(rs.getInt(6)).toString()));
            }
        } catch (SQLException | DatatypeConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return rel;
    }

    @Override
    public void update(Relation rel) {
        Statement stmt;
        String sql;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(rel.getTimestamp().toGregorianCalendar().getTimeInMillis());
            sql = "UPDATE RELATIONS SET ID = " + rel.getId() + "," + " VERSION = " + rel.getVersion() + ", " + " _TIMESTAMP = " + "'" + timestamp + "'" + ", " + " UID = " + rel.getUid() + ", " + " USER_NAME = " + "'" + rel.getUser().replace('\'', '.') + "'" + ", " + " CHANGESET = " + rel.getChangeset() + " WHERE ID = " + rel.getId() + ";";
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
    public void delete(int id) {
        try {
            PreparedStatement pStatement = conn.prepareStatement(
                    "DELETE FROM RELATIONS WHERE ID = ?");
            pStatement.setObject(1, id);
            pStatement.executeUpdate();
            conn.commit();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
