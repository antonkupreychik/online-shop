package com.kupreychik.userservice.mapper;

import com.kupreychik.userservice.model.command.UserCommand;
import com.kupreychik.userservice.model.dto.UserDto;
import com.kupreychik.userservice.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCommand userCommand);

    UserDto toUserDto(User user);
}
