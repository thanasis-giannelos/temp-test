package com.sakis.temptest.service;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import com.sakis.temptest.exception.ResourceAlreadyExistsException;
import com.sakis.temptest.exception.ResourceNotFoundException;
import com.sakis.temptest.mapper.UserMapper;
import com.sakis.temptest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDtos(users);
    }

    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName())) {
            throw new ResourceAlreadyExistsException();
        }
        User user = userMapper.userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    public UserDto getUser(Long userId) {
        return userMapper.userToUserDto(
                userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException())
        );
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException();
        }
        userRepository.deleteById(userId);
    }

//    public UserDto updateUser(Long userId, UserDto userDto) {
//        User oldUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
//        UserDto oldUserDto = userMapper.userToUserDto(oldUser);
//
//    }

}
