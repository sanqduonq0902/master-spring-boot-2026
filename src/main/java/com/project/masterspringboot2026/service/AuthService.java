package com.project.masterspringboot2026.service;

import com.project.masterspringboot2026.dto.request.AuthRequest;
import com.project.masterspringboot2026.exception.AppException;
import com.project.masterspringboot2026.exception.ErrorCode;
import com.project.masterspringboot2026.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserRepository userRepository;

     public boolean authenticate(AuthRequest request) {
         var user = userRepository.findByUsername(request.getUsername())
                 .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

         PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
         return passwordEncoder.matches(request.getPassword(), user.getPassword());
     }
}
