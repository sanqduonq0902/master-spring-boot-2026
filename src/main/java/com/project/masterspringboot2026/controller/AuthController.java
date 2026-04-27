package com.project.masterspringboot2026.controller;

import com.nimbusds.jose.JOSEException;
import com.project.masterspringboot2026.dto.request.AuthRequest;
import com.project.masterspringboot2026.dto.request.IntrospectRequest;
import com.project.masterspringboot2026.dto.request.LogoutRequest;
import com.project.masterspringboot2026.dto.response.APIResponse;
import com.project.masterspringboot2026.dto.response.AuthResponse;
import com.project.masterspringboot2026.dto.response.IntrospectResponse;
import com.project.masterspringboot2026.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/token")
    APIResponse<AuthResponse> authenticated(@RequestBody AuthRequest request) {
        var result = authService.authenticate(request);
        return APIResponse.<AuthResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> authenticated(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authService.introspect(request);

        return APIResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    APIResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authService.logout(request);
        return APIResponse.<Void>builder().build();
    }
}
