package com.spring.controller;

import com.spring.entity.NodeEntity;
import com.spring.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Node")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @GetMapping(value = "/AllNodes")
    public ResponseEntity<List<NodeEntity>> getAllNodes() {
        final List<NodeEntity> nodes = nodeService.findAll();
        return nodes != null && !nodes.isEmpty()
              ? new ResponseEntity<>(nodes, HttpStatus.OK)
              : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<NodeEntity> getNodeById(@PathVariable(name = "id") Long id) {
        final NodeEntity client = nodeService.findById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getAllNodesByRadius")
    ResponseEntity<List<NodeEntity>> getAllNodesByRadius(
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "lon", required = false) Double lon,
            @RequestParam(value = "radius", required = false) Long radius) {
        List<NodeEntity> nodes = nodeService.findByLocationAndRadius(54.87, 58.87, 1000000000L);
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }
    @PostMapping(value = "/createNode")
    public ResponseEntity<?> createNodeEntity(@RequestBody NodeEntity nodeEntity) {
        nodeService.createNodeEntity(nodeEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody NodeEntity nodeEntity) {
        nodeService.updateById(nodeEntity, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        nodeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}