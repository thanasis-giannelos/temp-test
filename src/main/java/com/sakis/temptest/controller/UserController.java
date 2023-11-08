package com.sakis.temptest.controller;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import com.sakis.temptest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<User> getUser(@PathVariable Long userId) {
//        User user = userService.getUser(userId);
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        UserDto newUserDto = userService.createUser(userDto);
        return new ResponseEntity<String>("user created\n"+newUserDto.toString(), HttpStatus.OK);
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity deleteUser(@RequestParam String userId) {
//        return ResponseEntity
//    }
//
//    @PatchMapping("/{userId}")
//    public ResponseEntity updateUser(@RequestParam String userId) {
//        return ResponseEntity
//    }

}
