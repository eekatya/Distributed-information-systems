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
import java.util.*;
import org.openstreetmap.osm._0.*;
public class XMlParser {
    public XMlParser() {

    }

    public void parseXML(String fileName) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlEventReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            JAXBContext context = JAXBContext.newInstance(Node.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            while (xmlEventReader.hasNext()) {
                int event = xmlEventReader.next();
                if (event == XMLEvent.START_ELEMENT && "node".equals(xmlEventReader.getLocalName())) {
                    Node node = (Node) unmarshaller.unmarshal(xmlEventReader);
                    System.out.println(node.getUser());

                }
            }

        } catch (FileNotFoundException | XMLStreamException | JAXBException e) {
            e.printStackTrace();
        }
    }
}


