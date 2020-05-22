package com.spring.service;

import com.spring.entity.RelationEntity;
import com.spring.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService {

    @Autowired
    private final RelationRepository relationRepository;

    public RelationService(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    public void createRelationEntity(RelationEntity relationEntity) {
        relationRepository.save(relationEntity);
    }
    public List<RelationEntity> findAll(){
        return relationRepository.findAll();
    }
    public RelationEntity findById(Long relationId){
        return relationRepository.findOne(relationId);
    }
    public void deleteById(Long relationId){
        relationRepository.delete(relationId);
    }
    public void updateById(RelationEntity relationEntity, Long id) {
        relationEntity.setId(id);
        relationRepository.save(relationEntity);
    }
    public List<RelationEntity> findAllByUser(String user){
        return relationRepository.findAllByUser(user);
    }
}