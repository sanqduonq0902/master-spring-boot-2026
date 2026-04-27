package com.project.masterspringboot2026.mapper;

import com.project.masterspringboot2026.dto.request.RoleRequest;
import com.project.masterspringboot2026.dto.response.RoleResponse;
import com.project.masterspringboot2026.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
