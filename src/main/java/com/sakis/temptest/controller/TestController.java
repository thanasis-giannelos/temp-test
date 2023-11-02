package com.sakis.temptest.controller;

import com.sakis.temptest.user.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
public class TestController {

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return new User("sakis", "kappas", 23);
    }

    @GetMapping
    public String getUsers(@RequestParam int page, @RequestParam(defaultValue = "20") int limit) {
        return "users" + page + " " + limit;
    }
    @PostMapping("/{userId}")
    public String createUser(@PathVariable String userId) {
        return userId;
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
