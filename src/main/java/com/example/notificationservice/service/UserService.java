package com.example.notificationservice.service;

import com.example.notificationservice.dto.UserDTO;
import com.example.notificationservice.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUsers();

    Optional<UserEntity> findById(Long id);

    UserDTO save(UserDTO userDTO);

    UserDTO update(Long id, UserDTO userDTO);

    void delete(Long id);
}
