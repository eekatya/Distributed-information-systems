package com.spring.controller;

import com.spring.entity.RelationEntity;
import com.spring.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Relation")
public class RelationController {
    @Autowired
    private RelationService relationService;

    @GetMapping(value = "/AllRelations")
    public ResponseEntity<List<RelationEntity>> getAllRelations() {
        final List<RelationEntity> relations = relationService.findAll();
        return relations != null && !relations.isEmpty()
                ? new ResponseEntity<>(relations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<RelationEntity> getRelationById(@PathVariable(name = "id") Long id) {
        final RelationEntity client = relationService.findById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/createRelation")
    public ResponseEntity<?> createRelationEntity(@RequestBody RelationEntity relationEntity) {
        relationService.createRelationEntity(relationEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody RelationEntity relationEntity) {
        relationService.updateById(relationEntity, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        relationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
