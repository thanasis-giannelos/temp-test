package com.sakis.temptest.service;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import com.sakis.temptest.mapper.UserMapper;
import com.sakis.temptest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("jcoije");
        user.setGender("Male");

        userDto = new UserDto();
        userDto.setFirstName("jcoije");
        userDto.setGender("Male");

    }

    @Test
    public void givenUsersList_whenGetAllUsers_thenReturnUsersList() {

        //given
        given(userRepository.findAll()).willReturn(List.of(user));
        given(userMapper.usersToUserDtos(List.of(user))).willReturn(List.of(userDto));

        //when - ayto pou ontws testarw
        List<UserDto> userss = userService.getUsers();
        //then
        assertThat(userss).hasSize(1);
    }
}
