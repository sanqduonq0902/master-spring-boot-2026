package com.project.masterspringboot2026.service;

import com.project.masterspringboot2026.dto.request.PermissionRequest;
import com.project.masterspringboot2026.dto.response.PermissionResponse;
import com.project.masterspringboot2026.entity.Permission;
import com.project.masterspringboot2026.mapper.PermissionMapper;
import com.project.masterspringboot2026.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }
}
