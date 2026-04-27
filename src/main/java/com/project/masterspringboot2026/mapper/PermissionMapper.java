package com.project.masterspringboot2026.mapper;

import com.project.masterspringboot2026.dto.request.PermissionRequest;
import com.project.masterspringboot2026.dto.response.PermissionResponse;
import com.project.masterspringboot2026.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
