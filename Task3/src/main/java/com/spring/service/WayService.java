package com.spring.service;

import com.spring.entity.WayEntity;
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
    public List<WayEntity> findAll(){
        return wayRepository.findAll();
    }
    public WayEntity findById(Long wayId){
        return wayRepository.findOne(wayId);
    }
    public void deleteById(Long wayId){
        wayRepository.delete(wayId);
    }
    public void updateById(WayEntity wayEntity, Long id) {
        wayEntity.setId(id);
        wayRepository.save(wayEntity);
    }
    public List<WayEntity> findAllByUser(String user){
        return wayRepository.findAllByUser(user);
    }


}