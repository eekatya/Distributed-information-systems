package com.spring.repository;

import com.spring.entity.TagEntity;
import com.spring.entity.WayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

}