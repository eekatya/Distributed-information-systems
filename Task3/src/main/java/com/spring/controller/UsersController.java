package com.spring.controller;

import com.spring.entity.Users;
import com.spring.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path = "api", method = {RequestMethod.GET})
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<Users> getAllUsers() {
        return userService.findAll();
    }
   @GetMapping("{id}")
   public Users get(@PathVariable(name = "id") Long id) {
       System.out.println("Find user");
       userService.findById(id).print();
       return userService.findById(id);
   }
    /*@GetMapping(value = "/users")
    public ResponseEntity<List<Users>> read() {
        final List<Users> clients = userService.findAll();
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
    @PostMapping("/users")
    ResponseEntity<Void> createUser(@RequestBody Users user) {
        userService.createUsers(user);
        return ResponseEntity.ok().build();
    }
}