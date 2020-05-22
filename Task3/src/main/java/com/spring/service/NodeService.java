package com.spring.service;

import com.spring.entity.NodeEntity;
import com.spring.entity.Users;
import com.spring.repository.NodeRepository;
import com.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   /* public void update(NodeEntity nodeEntity){
        nodeRepository.save(nodeEntity);
    }*/
    public List<NodeEntity> findByLocationAndRadius(Double lat, Double lon, Long radius)
    {
        return  nodeRepository.findByLocationAndRadius(lat, lon, radius);
    }

    public void updateById(NodeEntity nodeEntity, Long id) {
        nodeEntity.setId(id);
        nodeRepository.save(nodeEntity);
    }

   /* public List<NodeEntity> findAllByName(String name){
        return nodeRepository.findAllByName(name);
    }*/
}
