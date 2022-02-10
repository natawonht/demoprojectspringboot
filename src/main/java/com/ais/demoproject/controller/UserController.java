package com.ais.demoproject.controller;

import java.util.ArrayList;
import java.util.List;

import com.ais.demoproject.model.Result;
import com.ais.demoproject.model.User;
import com.ais.demoproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;

    String success = "SUCCESS";
    
    @GetMapping("/hello")
    public ResponseEntity<Result> getHello() {
        String speech = userService.hello();
        return ResponseEntity.ok().body(new Result(HttpStatus.OK.value(), speech, new ArrayList<>()));
    }

    @GetMapping("/sayHello")
    public ResponseEntity<Result> getSayHello(@RequestParam String userName) {
        String speech = userService.sayHello(userName);
        return ResponseEntity.ok().body(new Result(HttpStatus.OK.value(), speech, new ArrayList<>()));
    }

    @PostMapping("/addUser")
    public ResponseEntity<Result> addUser(@RequestBody User userData) {
        userService.addUser(userData);
        return ResponseEntity.ok().body(new Result(HttpStatus.OK.value(), success, new ArrayList<>()));
    }

    @GetMapping("/getUser")
    public ResponseEntity<Result> getUser() {
        List<User> users = userService.getUser();
        return ResponseEntity.ok().body(new Result(HttpStatus.OK.value(), success, users));
    }

    @GetMapping("/getUser/{userName}")
    public ResponseEntity<Result> getUserByUserName(@PathVariable String userName) {
        User user = userService.getUserById(userName);
        return ResponseEntity.ok().body(new Result(HttpStatus.OK.value(), success, user));
    }
}
