package com.sakis.temptest.service;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import com.sakis.temptest.exception.ResourceAlreadyExistsException;
import com.sakis.temptest.exception.ResourceNotFoundException;
import com.sakis.temptest.mapper.UserMapper;
import com.sakis.temptest.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    // test dependencies
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    // class to be tested
    @InjectMocks
    private UserService userService;

    // test data
    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("jcoije");
        user.setGender("Male");
        user.setId(23L);

        userDto = new UserDto();
        userDto.setFirstName("jcoije");
        userDto.setGender("Male");

    }

    @Test
    void givenUsersList_whenGetAllUsers_thenReturnUsersList() {

        //given
        given(userRepository.findAll()).willReturn(List.of(user));
        given(userMapper.usersToUserDtos(List.of(user))).willReturn(List.of(userDto));

        //when - ayto pou ontws testarw
        List<UserDto> userss = userService.getUsers();
        //then
        assertThat(userss).hasSize(1);
    }

    @Test
    void givenEmptyUsersList_whenGetAllUsers_thenReturnEmptyUsersList() {

        //given
        given(userRepository.findAll()).willReturn(List.of());
        given(userMapper.usersToUserDtos(List.of())).willReturn(List.of());

        //when - ayto pou ontws testarw
        List<UserDto> returnedUsers = userService.getUsers();

        //then
        assertThat(returnedUsers).isEmpty();
    }

    @Test
    void givenUserDto_whenCreateUser_thenNewUserDto() {

        //given
        given(userRepository.existsByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName()))
                .willReturn(false);
        given(userMapper.userDtoToUser(userDto))
                .willReturn(user);
        given(userRepository.save(user))
                .willReturn(user);
        given(userMapper.userToUserDto(user))
                .willReturn(userDto);

        //when - ayto pou ontws testarw
        UserDto newUserDto = userService.createUser(userDto);

        //then
        assertThat(newUserDto).isNotNull();
    }

    @Test
    void givenExistingUserDto_whenCreateUser_thenThrowResourceAlreadyExistsException() {

        //given
        given(userRepository.existsByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName()))
                .willReturn(true);

        //when - ayto pou ontws testarw
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            userService.createUser(userDto);
        });

        //then

    }

    @Test
    void givenExistingUserId_whenDeleteUser_thenDoNothing() {

        //given
        given(userRepository.existsById(user.getId())).willReturn(true);
        willDoNothing().given(userRepository).deleteById(user.getId());

        //when - ayto pou ontws testarw
        userService.deleteUser(user.getId());

        //then
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    void givenNonExistingUserId_whenDeleteUser_thenThrowException() {

        //given
        given(userRepository.existsById(9L)).willReturn(false);

        //when - ayto pou ontws testarw
        Assertions.assertThrows(ResourceNotFoundException.class, () ->userService.deleteUser(9L));

        //then
//        verify(userRepository, times(0)).deleteById(user.getId());
    }

    @Test
    void givenExistingUserId_whenGetUser_thenReturnUser() {
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(userMapper.userToUserDto(user)).willReturn(userDto);

        UserDto userDto = userService.getUser(1L);

        assertThat(userDto).isNotNull();
    }

    @Test
    void givenNonExistingUserId_whenGetUser_thenReturnException() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () ->userService.getUser(1L));

        verify(userMapper, never()).userToUserDto(user);
    }
}
