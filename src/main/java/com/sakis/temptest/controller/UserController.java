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

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.getUser(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        UserDto newUserDto = userService.createUser(userDto);
        return new ResponseEntity<String>("user created\n"+newUserDto.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("user deleted successfully", HttpStatus.OK);
    }

//    @PutMapping("/{userId}")
//    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
//        UserDto updatedUserDto = userService.updateUser(userDto);
//        return new ResponseEntity<String>("user updated\n"+updatedUserDto.toString(), HttpStatus.OK);
//    }


}
