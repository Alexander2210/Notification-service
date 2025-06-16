package com.example.notificationservice.service;

import com.example.notificationservice.dto.UserDTO;
import com.example.notificationservice.entity.UserEntity;
import com.example.notificationservice.mapper.UserMapper;
import com.example.notificationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        UserEntity user = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    UserEntity updatedUser = userMapper.toEntity(dto);
                    updatedUser.setId(id);
                    return userMapper.toDto(userRepository.save(updatedUser));
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
