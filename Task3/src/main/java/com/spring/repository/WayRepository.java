package com.spring.repository;

import com.spring.entity.WayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WayRepository extends JpaRepository<WayEntity, Long> {
    List<WayEntity> findAllByUser(String user);
}
