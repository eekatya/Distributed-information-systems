package com.spring.service;

import com.spring.entity.NodeEntity;
import com.spring.entity.RelationEntity;
import com.spring.entity.Users;
import com.spring.entity.WayEntity;
import com.spring.repository.NodeRepository;
import com.spring.repository.RelationRepository;
import com.spring.repository.UsersRepository;
import com.spring.repository.WayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WayService {

    @Autowired
    private final WayRepository wayRepository;

    public WayService(WayRepository wayRepository) {
        this.wayRepository = wayRepository;
    }

    public void createWayEntity(WayEntity wayEntity) {
        wayRepository.save(wayEntity);
    }
}