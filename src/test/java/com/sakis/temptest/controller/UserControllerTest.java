package com.sakis.temptest.controller;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import com.sakis.temptest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)                                     //load only the controller, not other layers
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = new User("ccece", "coie", "23r34r", "Male");
        userDto = new UserDto("ccece", "coie", "Male");
    }

    @Test
    public void givenUserDto_whenCreateUser_thenReturnSavedUserDto() throws Exception {

        //given
        given(userService.createUser(any(UserDto.class))).willReturn(userDto);

        //when - ayto pou ontws testarw
        ResultActions response = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        //then
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
                .andExpect(jsonPath("$.gender", is(userDto.getGender())));
    }

    @Test
    public void givenUsersDtoList_whenGetAllUsers_thenReturnUsersDto() throws Exception {

        //given
        given(userService.getUsers()).willReturn(List.of(userDto));

        //when - ayto pou ontws testarw
        ResultActions response = mockMvc.perform(get("/api/v1/users"));

        //then
        response.andExpect(status().isOk());
    }

}
