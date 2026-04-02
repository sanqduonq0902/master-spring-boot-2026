package com.project.masterspringboot2026.controller;

import com.project.masterspringboot2026.dto.request.AuthRequest;
import com.project.masterspringboot2026.dto.response.APIResponse;
import com.project.masterspringboot2026.dto.response.AuthResponse;
import com.project.masterspringboot2026.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    APIResponse<AuthResponse> authenticated(@RequestBody AuthRequest request) {
        boolean result = authService.authenticate(request);
        return APIResponse.<AuthResponse>builder()
                .result(AuthResponse.builder().authenticated(result).build())
                .build();
    }
}
