package com.example.notificationservice.controller;

import com.example.notificationservice.dto.UserDTO;
import com.example.notificationservice.mapper.UserMapper;
import com.example.notificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userMapper.toDtos(userService.findAllUsers());
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.save(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
