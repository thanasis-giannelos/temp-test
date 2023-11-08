package com.sakis.temptest.mapper;

import com.sakis.temptest.dto.UserDto;
import com.sakis.temptest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> usersToUserDtos(List<User> users);

    List<User> userDtosToUsers(List<UserDto> usersDtos);
}
