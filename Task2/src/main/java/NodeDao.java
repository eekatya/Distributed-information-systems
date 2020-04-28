import org.openstreetmap.osm._0.Node;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class NodeDao extends DAO<Node> {
    private postgreDatabase database;
    private Connection conn;
    private SpeedCalculation calc;

    NodeDao(Connection conn) {
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
                pStatement.setString(3, node.getTimestamp().toString());
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
            pStatement.setString(3, node.getTimestamp().toString());
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
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            Timestamp timestamp = new Timestamp(node.getTimestamp().toGregorianCalendar().getTimeInMillis());
           System.out.println(timestamp.toString());

            /* ZonedDateTime dateTime = node.getDateTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(dateTime.getZone()));*/
      /*      LocalDate date = LocalDate.of(node.getTimestamp().getYear(), node.getTimestamp().getMonth(), node.getTimestamp().getDay());
            LocalTime time = LocalTime.of(node.getTimestamp().getHour(),node.getTimestamp().getMinute(),node.getTimestamp().getSecond());
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            ZoneId zoneId = ZoneId.of(String.valueOf(node.getTimestamp().getTimezone()));
            ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime,zoneId);
            String str = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssz"));
*/
            /*Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(zoneId));*/
            //node.getTimestamp().
            //sql = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (" + node.getId() + ", " + node.getVersion() + ", " + "'" + node.getTimestamp().toString().replace(',', '.') + "'" + ", " + node.getUid() + ", " + "'" + node.getUser().replace('\'', '.') + "'" + ", " + node.getChangeset() + ", " + node.getLat().toString().replace(',', '.') + ", " + node.getLon().toString().replace(',', '.') + ");";
            sql = "INSERT INTO NODES (ID, VERSION, _TIMESTAMP, UID, USER_NAME, CHANGESET, LAT, LON) VALUES (" + node.getId() + ", " + node.getVersion() + ", " + "'" + timestamp + "'" + ", " + node.getUid() + ", " + "'" + node.getUser().replace('\'', '.') + "'" + ", " + node.getChangeset() + ", " + node.getLat().toString().replace(',', '.') + ", " + node.getLon().toString().replace(',', '.') + ");";

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

    public long getInsertTime() {
        return calc.timeCalculate();
    }

    public Node getById(int id) {
        Node node = new Node();
       /* try  {
            PreparedStatement statement =
                    conn.prepareStatement("SELECT * FROM NODES WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                node.setId(new BigInteger(Integer.valueOf(rs.getInt(1)).toString()));
                node.setVersion(new BigInteger(Integer.valueOf(rs.getInt(2)).toString()));
                //node.set

                node.setUid(new BigInteger(Integer.valueOf(rs.getInt(4)).toString()));
                node.setUser(rs.getString(5));
                node.setChangeset(new BigInteger(Integer.valueOf(rs.getInt(6)).toString()));
                node.setLat(rs.getDouble(7));
                node.setLon(rs.getDouble(7));
                //node = new Node(id, rs.getBigDecimal(2), rs.getString(3), rs.getBigDecimal(4), rs.getString(5), rs.getBigDecimal(6), rs.getDouble(7));
            }
        } catch (SQLException | ParseException | DatatypeConfigurationException e) {
            System.out.println(e.getMessage());
        }*/
        return node;

    }
    public  void update(Node el)
    {

    }
    public  void delete(int id)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM NODES WHERE id = ?");
            stmt.setObject(1, id);
            stmt.executeUpdate();
            conn.commit();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
