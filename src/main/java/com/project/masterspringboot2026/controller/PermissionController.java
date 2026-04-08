package com.project.masterspringboot2026.controller;

import com.project.masterspringboot2026.dto.request.PermissionRequest;
import com.project.masterspringboot2026.dto.response.APIResponse;
import com.project.masterspringboot2026.dto.response.PermissionResponse;
import com.project.masterspringboot2026.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    APIResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return APIResponse.<PermissionResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Created permission successfully")
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    APIResponse<List<PermissionResponse>> getAll() {
        return APIResponse.<List<PermissionResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all permission successfully")
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    APIResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return APIResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Deleted permission successfully")
                .build();
    }
}
