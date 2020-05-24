package com.spring.service;

import com.spring.entity.*;
import com.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void createTagEntity(TagEntity wayEntity) {
        tagRepository.save(wayEntity);
    }

}