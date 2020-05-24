package com.spring.service;


import com.spring.entity.NodeEntity;
import com.spring.org.openstreetmap.osm._0.Node;
import com.spring.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class NodeService {

    @Autowired
    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository){
        this.nodeRepository = nodeRepository;
    }

    public void createNodeEntity(NodeEntity nodeEntity) {
        nodeRepository.save(nodeEntity);
    }
    public List<NodeEntity> findAll(){
        return nodeRepository.findAll();
    }
    public NodeEntity findById(Long nodeId){
        return nodeRepository.findOne(nodeId);
    }
    public void deleteById(Long nodeId){
        nodeRepository.delete(nodeId);
    }
    public List<NodeEntity> getAllNodesByRadius(Double lat, Double lon, Long radius)
    {
        return  nodeRepository.getAllNodesByRadius(lat, lon, radius);
    }

    public void updateById(NodeEntity nodeEntity, Long id) {
        nodeEntity.setId(id);
        nodeRepository.save(nodeEntity);
    }

    public List<NodeEntity> findAllByUser(String user){
        return nodeRepository.findAllByUser(user);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void insertData(){
      /*  XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlEventReader = xmlInputFactory.createXMLStreamReader(new FileInputStream("RU-NVS.osm"));
            JAXBContext contextNode = JAXBContext.newInstance(Node.class);
            Unmarshaller unmarshalNode = contextNode.createUnmarshaller();
            while (xmlEventReader.hasNext()) {
                int event = xmlEventReader.next();
                if (event == XMLEvent.START_ELEMENT && "node".equals(xmlEventReader.getLocalName())) {
                    Node node = (Node) unmarshalNode.unmarshal(xmlEventReader);
                    NodeEntity nodeEntity = new NodeEntity(node);
                    nodeRepository.save(nodeEntity);
                }

            }
        } catch (FileNotFoundException | XMLStreamException | JAXBException e) {
            e.printStackTrace();
        }*/

    }
}
