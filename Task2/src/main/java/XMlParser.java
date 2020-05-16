
import dao.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import org.openstreetmap.osm._0.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMlParser {
    private static Logger LOGGER = LoggerFactory.getLogger(XMlParser.class);
    private PostgreDatabase database;
    private static final String typeOfInsert = "USING_STATEMENT";
    //private static final String typeOfInsert = "USING_PREPARED_STATEMENT";
    //private static final String typeOfInsert = "USING_BATCH";
    XMlParser()
    {
        init();
    }

    private void init() {
        database = new PostgreDatabase();
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
        int countNode = 0;
        int countWay = 0;
        NodeDao nodeDao = null;
        WayDAO wayDAO = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlEventReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            JAXBContext contextNode = JAXBContext.newInstance(Node.class);
            JAXBContext contextWay = JAXBContext.newInstance(Way.class);
            Unmarshaller unmarshalNode = contextNode.createUnmarshaller();
            Unmarshaller unmarshalWay = contextWay.createUnmarshaller();
            nodeDao = new NodeDao(database.getConnection());
            wayDAO = new WayDAO(database.getConnection());
            ArrayList<Node> listNodes = new ArrayList<>();
            ArrayList<Way> listWays = new ArrayList<>();
            while (xmlEventReader.hasNext()) {
                int event = xmlEventReader.next();
                if (event == XMLEvent.START_ELEMENT && "node".equals(xmlEventReader.getLocalName())) {
                    Node node = (Node) unmarshalNode.unmarshal(xmlEventReader);
                    switch (typeOfInsert)
                    {
                        case("USING_STATEMENT"):
                            nodeDao.insertStatement(node);
                            break;
                        case("USING_PREPARED_STATEMENT"):
                            nodeDao.insertPreparedStatement(node);
                            break;
                        case("USING_BATCH"):
                            listNodes.add(node);
                            countNode++;
                            if (countNode==10)
                            {
                                countNode = 0;
                                nodeDao.insertBatch(listNodes);
                                listNodes = new ArrayList<>();
                            }
                            break;
                        default:
                            LOGGER.error("Uncorrected type of insert");
                            break;
                    }
                }
                else if (event == XMLEvent.START_ELEMENT && "way".equals(xmlEventReader.getLocalName()))
                {
                    Way way = (Way) unmarshalWay.unmarshal(xmlEventReader);
                    switch (typeOfInsert)
                    {
                        case("USING_STATEMENT"):
                            wayDAO.insertStatement(way);
                            break;
                        case("USING_PREPARED_STATEMENT"):
                            wayDAO.insertPreparedStatement(way);
                            break;
                        case("USING_BATCH"):
                            listWays.add(way);
                            countWay++;
                            if (countWay==10)
                            {
                                countWay = 0;
                                wayDAO.insertBatch(listWays);
                                listWays = new ArrayList<>();
                            }
                            break;
                        default:
                            LOGGER.error("Uncorrected type of insert");
                            break;
                    }
                }
            }
            if (typeOfInsert.equals("USING_BATCH"))
            {
                if (countNode!=0)
                    nodeDao.insertBatch(listNodes);
                if (countWay!=0)
                  wayDAO.insertBatch(listWays);
            }
            LOGGER.info("Insertion speed of nodes: " + nodeDao.getInsertTime() + " records per second");
            LOGGER.info("Insertion speed of ways: " + wayDAO.getInsertTime() + " records per second");
            if (database.getConnection()!=null)
            database.disconnectDatabase();
        } catch (FileNotFoundException | XMLStreamException | JAXBException  e) {
            if (database.getConnection()!=null)
                database.disconnectDatabase();
            e.printStackTrace();
        }

    }
}


