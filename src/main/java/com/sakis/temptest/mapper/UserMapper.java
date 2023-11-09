package com.sakis.temptest.mapper;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> usersToUserDtos(List<User> users);

}
