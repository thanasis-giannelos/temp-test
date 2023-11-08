package com.sakis.temptest.service;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import com.sakis.temptest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers() {
        List<User> users =  userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(new UserDto(user.getFirstName(), user.getLastName())));
        return userDtos;
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(), null);
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getFirstName(), savedUser.getLastName());
    }

//    public User updateUser(User user) {
//        return userRepository.save(user);
//    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        userRepository.deleteById(userId);
    }

}
