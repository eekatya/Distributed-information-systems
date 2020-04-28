import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class XMlParser {
    private static Logger LOGGER = LoggerFactory.getLogger(XMlParser.class);
    private Map<String, Integer> userCorr;
    private Map<String, Integer> uniqueKeys;
    public XMlParser()
    {
        userCorr = new TreeMap<>();
        uniqueKeys = new TreeMap<>();
    }
    public void checkUniqueKeys(String name, StartElement startElement)
    {
        //LOGGER.info("Unique key calculation");
        Attribute attrb = startElement.getAttributeByName(new QName(name));
        if (attrb!=null)
        {
            int num;
            String key;
            key = attrb.getValue();
            if (uniqueKeys.containsKey(key))
            {
                num = uniqueKeys.get(key);
                num+=1;
            }
            else
                num = 1;
            uniqueKeys.put(key,num);
        }
    }
    public void parseXML(String fileName) {
        LOGGER.info("Start parsing");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("node")) {
                     /*   checkUniqueKeys("id", startElement);
                        checkUniqueKeys("version", startElement);
                        checkUniqueKeys("timestamp", startElement);*/
                        checkUniqueKeys("uid", startElement);
                     /*   checkUniqueKeys("user", startElement);
                        checkUniqueKeys("changeset", startElement);
                        checkUniqueKeys("lat", startElement);
                        checkUniqueKeys("lon", startElement);*/
                        calculCorrect(startElement);
                    }
                }
            }
            LOGGER.info("Stop parsing");
            for (Map.Entry entry : uniqueKeys.entrySet()) {
                System.out.println("The name of key: " + entry.getKey().toString() + " - " + "number of tags that contain this key: " + entry.getValue());
            }
            userCorr.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .map(e -> "The name of user: " + e.getKey() + " - number of corrections: " + e.getValue())
                    .forEach(System.out::println);
        }catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void calculCorrect(StartElement startElement) {
        //LOGGER.info("User corrections calculation");
        Attribute attrb = startElement.getAttributeByName(new QName("user"));
        if (attrb!=null)
        {
            int corr;
            if (userCorr.containsKey(attrb.getValue()))
            {
                corr = userCorr.get(attrb.getValue());
                userCorr.put(attrb.getValue(), corr + 1);
            }
            else
                userCorr.put(attrb.getValue(), 1);
        }
    }
}
