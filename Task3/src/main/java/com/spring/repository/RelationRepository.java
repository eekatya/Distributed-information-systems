package com.spring.repository;

import com.spring.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<RelationEntity, Long> {
    List<RelationEntity> findAllByUser(String user);
}