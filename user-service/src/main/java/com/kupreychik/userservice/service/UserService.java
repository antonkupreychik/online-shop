package com.kupreychik.userservice.service;

import com.kupreychik.userservice.model.command.UserCommand;
import com.kupreychik.userservice.model.dto.UserDto;
import com.kupreychik.userservice.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getUsers(Pageable pageable);

    UserDto registerUser(UserCommand newUser);

    boolean authenticateUser(String username, String passwordHash);

    User findUserById(Long id);

    User updateUser(Long id, User userDetails);

    boolean deleteUser(Long id);
}
