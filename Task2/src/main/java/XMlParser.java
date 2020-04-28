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
import java.sql.SQLException;
import java.util.*;
import org.openstreetmap.osm._0.*;
public class XMlParser {
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
                    listNodes.add(node);
                    nodeDao.insertStatement(node);
                   // nodeDao.insertPreparedStatement(node);
                    count++;
                    if (count==10)
                    {
                        count = 0;
                       // nodeDao.insertBatch(listNodes);
                        listNodes = new ArrayList<>();
                    }
                }
            }
           // if (count!=0)
              //  nodeDao.insertBatch(listNodes);
            System.out.println("Insertion speed: " + nodeDao.getInsertTime() + " records per second");
            if (database.getConnection()!=null)
            database.disconnectDatabase();
        } catch (FileNotFoundException | XMLStreamException | JAXBException  e) {
            e.printStackTrace();
        }

    }
}


