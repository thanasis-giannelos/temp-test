package com.sakis.temptest.controller;

import com.sakis.temptest.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = new User("sakis", "kappas", 23);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping
    public String getUsers(@RequestParam int page, @RequestParam(defaultValue = "20") int limit) {
        return "users" + page + " " + limit;
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@Valid @RequestBody User newUser) {

        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return userId;
    }

}
