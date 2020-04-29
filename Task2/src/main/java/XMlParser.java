
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
    private postgreDatabase database;
    private static final String typeOfInsert = "USING_STATEMENT";
   // private static final String typeOfInsert = "USING_PREPARED_STATEMENT";
  //  private static final String typeOfInsert = "USING_BATCH";
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
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlEventReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            JAXBContext context = JAXBContext.newInstance(Node.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            nodeDao = new NodeDao(database.getConnection());
            ArrayList<Node> listNodes = new ArrayList<>();
            while (xmlEventReader.hasNext()) {
                int event = xmlEventReader.next();
                if (event == XMLEvent.START_ELEMENT && "node".equals(xmlEventReader.getLocalName())) {
                    Node node = (Node) unmarshaller.unmarshal(xmlEventReader);
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
                            count++;
                            if (count==10)
                            {
                                count = 0;
                                nodeDao.insertBatch(listNodes);
                                listNodes = new ArrayList<>();
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
                if (count!=0)
                  nodeDao.insertBatch(listNodes);
            }
            LOGGER.info("Insertion speed: " + nodeDao.getInsertTime() + " records per second");
            if (database.getConnection()!=null)
            database.disconnectDatabase();
        } catch (FileNotFoundException | XMLStreamException | JAXBException  e) {
            if (database.getConnection()!=null)
                database.disconnectDatabase();
            e.printStackTrace();
        }

    }
}


