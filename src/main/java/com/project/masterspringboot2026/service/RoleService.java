package com.project.masterspringboot2026.service;

import com.project.masterspringboot2026.dto.request.RoleRequest;
import com.project.masterspringboot2026.dto.response.RoleResponse;
import com.project.masterspringboot2026.mapper.RoleMapper;
import com.project.masterspringboot2026.repository.PermissionRepository;
import com.project.masterspringboot2026.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
         var role = roleMapper.toRole(request);

        var permission =  permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper:: toRoleResponse)
                .toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
