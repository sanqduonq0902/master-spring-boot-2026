package com.project.masterspringboot2026.controller;

import com.project.masterspringboot2026.dto.request.RoleRequest;
import com.project.masterspringboot2026.dto.response.APIResponse;
import com.project.masterspringboot2026.dto.response.RoleResponse;
import com.project.masterspringboot2026.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    APIResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        var result = roleService.create(request);
        return APIResponse.<RoleResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Created role successfully")
                .result(result)
                .build();
    }

    @GetMapping
    APIResponse<List<RoleResponse>> getAll() {
        var result = roleService.getAll();
        return APIResponse.<List<RoleResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all role successfully")
                .result(result)
                .build();
    }

    @DeleteMapping("/{role}")
    APIResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return APIResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Deleted role successfully")
                .build();
    }
}
