package com.spring.controller;

import com.spring.entity.NodeEntity;
import com.spring.entity.Users;
import com.spring.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/node")
public class NodeController {
    @Autowired
    private NodeService nodeService;
    @GetMapping("/users")
    List<NodeEntity> getAllNodeEntity() {
        return nodeService.findAll();
    }
    @GetMapping("{id}")
    public NodeEntity get(@PathVariable(name = "id") Long id) {
        return nodeService.findById(id);
    }
    @GetMapping("/findByLocationAndRadius")
    List<NodeEntity> findByLocationAndRadius(
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "lon", required = false) Double lon,
            @RequestParam(value = "radius", required = false) Long radius) {
    return  nodeService.findByLocationAndRadius(54.87, 58.87, 1000000000L);
    }

   /* @GetMapping("/getNodes")
    public ResponseEntity<List<NodeEntity>> getNodesByLocationInRadius(
            @RequestParam(value = "lat", required = false) Float lat,
            @RequestParam(value = "lon", required = false) Float lon,
            @RequestParam(value = "radius", required = false) Integer radius) {
        List<NodeEntity> nodes = nodeService.getNodesByLocationInRadius(54.87f, 58.87f, 100000);
        //List<NodeEntity> nodes = osmService.getNodesByLocationInRadius(lat, lon, radius, page);
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }*/
 /*   @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

   @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody NodeEntity nodeEntity) {
        nodeService.create(nodeEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<NodeEntity>> read() {
        final List<NodeEntity> clients = nodeService.readAll();
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
        final Client client = clientService.read(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/

}