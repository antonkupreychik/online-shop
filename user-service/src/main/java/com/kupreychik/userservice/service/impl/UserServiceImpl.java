package com.kupreychik.userservice.service.impl;

import com.kupreychik.userservice.mapper.UserMapper;
import com.kupreychik.userservice.model.command.UserCommand;
import com.kupreychik.userservice.model.dto.UserDto;
import com.kupreychik.userservice.model.entity.User;
import com.kupreychik.userservice.repository.UserRepository;
import com.kupreychik.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public UserDto registerUser(UserCommand newUser) {
        User user = userMapper.toUser(newUser);
        user.setPasswordHash(passwordEncoder.encode(newUser.getPassword()));

        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new DataIntegrityViolationException("Username '" + newUser.getUsername() + "' is already taken.");
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new DataIntegrityViolationException("Email '" + newUser.getEmail() + "' is already in use.");
        }

        return userMapper.toUserDto(userRepository.save(user));
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(u -> passwordEncoder.matches(password, u.getPasswordHash())).orElse(false);
    }

    @Transactional
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(userDetails.getEmail());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            // Дополнительные поля можно обновлять здесь
            return userRepository.save(user);
        }).orElse(null);
    }


    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
