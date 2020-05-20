package com.spring.service;

import com.spring.entity.NodeEntity;
import com.spring.entity.RelationEntity;
import com.spring.entity.Users;
import com.spring.repository.NodeRepository;
import com.spring.repository.RelationRepository;
import com.spring.repository.UsersRepository;
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
}