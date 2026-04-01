package com.project.masterspringboot2026.service;

import com.project.masterspringboot2026.dto.request.UserCreationRequest;
import com.project.masterspringboot2026.dto.request.UserUpdateRequest;
import com.project.masterspringboot2026.entity.User;
import com.project.masterspringboot2026.exception.AppException;
import com.project.masterspringboot2026.exception.ErrorCode;
import com.project.masterspringboot2026.mapper.UserMapper;
import com.project.masterspringboot2026.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String id, UserUpdateRequest request) {
        User user = getUserById(id);

        userMapper.updateUser(user, request);

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
