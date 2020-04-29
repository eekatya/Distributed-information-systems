import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;
import org.openstreetmap.osm._0.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMlParser {
    private static Logger LOGGER = LoggerFactory.getLogger(XMlParser.class);
    private postgreDatabase database;
    XMlParser()
    {
        init();
    }

    private void init() {
        database = new postgreDatabase();
        try {
            if (database.getConnection()!=null)
                database.disconnectDatabase();
            database.dropDatabase();
            database.createDatabase();
            database.createAllTables();
        } catch (Exception e) {
            if (database.getConnection()!=null)
                database.disconnectDatabase();
            e.printStackTrace();
        }
    }

    public void parseXML(String fileName) {
        int count = 0;
        NodeDao nodeDao = null;
        WayDAO wayDAO = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlEventReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            JAXBContext context = JAXBContext.newInstance(Node.class);
            JAXBContext context1 = JAXBContext.newInstance(Way.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Unmarshaller unmarshaller1 = context1.createUnmarshaller();
            nodeDao = new NodeDao(database.getConnection());
            wayDAO = new WayDAO(database.getConnection());
            ArrayList<Node> listNodes = new ArrayList<>();
            while (xmlEventReader.hasNext()) {
                int event = xmlEventReader.next();
                if (event == XMLEvent.START_ELEMENT && "node".equals(xmlEventReader.getLocalName())) {
                    Node node = (Node) unmarshaller.unmarshal(xmlEventReader);
                    listNodes.add(node);
                   // nodeDao.insertStatement(node);
                   // nodeDao.insertPreparedStatement(node);
                    count++;
                    if (count==10)
                    {
                        count = 0;
                      //  nodeDao.insertBatch(listNodes);
                        listNodes = new ArrayList<>();
                    }
                }
                else if (event == XMLEvent.START_ELEMENT && "way".equals(xmlEventReader.getLocalName()))
                {
                    Way way = (Way) unmarshaller1.unmarshal(xmlEventReader);
                    wayDAO.insertStatement(way);
                    wayDAO.delete(216638602);
                    Way way1 = wayDAO.getById(216638603);
                    way1.setUser("referfr");
                    wayDAO.update(way1);
                }
            }
          //  if (count!=0)
              //  nodeDao.insertBatch(listNodes);
            LOGGER.info("Insertion speed: " + nodeDao.getInsertTime() + " records per second");

            // nodeDao.delete(32521222);
          //  Node node1 = nodeDao.getById(32521222);
          //  node1.setLat(1.2323);
         //   node1.setLon(4.343);
           // BigInteger u = new BigInteger(String.valueOf(2));
           // node1.setId(u);
           // node1.setUser("User");
           // nodeDao.update(node1);
           // System.out.println("Id: " + node1.getId() + " User: " + node1.getUser() + " Timestamp: " + node1.getTimestamp());
            if (database.getConnection()!=null)
            database.disconnectDatabase();
        } catch (FileNotFoundException | XMLStreamException | JAXBException  e) {
            if (database.getConnection()!=null)
                database.disconnectDatabase();
            e.printStackTrace();
        }

    }
}


