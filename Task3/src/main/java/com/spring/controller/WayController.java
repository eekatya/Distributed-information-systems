package com.spring.controller;

import com.spring.entity.WayEntity;
import com.spring.service.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Way")
public class WayController {
    @Autowired
    private WayService wayService;

    @GetMapping(value = "/AllWays")
    public ResponseEntity<List<WayEntity>> getAllWays() {
        final List<WayEntity> ways = wayService.findAll();
        return ways != null && !ways.isEmpty()
                ? new ResponseEntity<>(ways, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<WayEntity> getWayById(@PathVariable(name = "id") Long id) {
        final WayEntity client = wayService.findById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/createWay")
    public ResponseEntity<?> createWayEntity(@RequestBody WayEntity wayEntity) {
        wayService.createWayEntity(wayEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody WayEntity wayEntity) {
        wayService.updateById(wayEntity, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        wayService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
