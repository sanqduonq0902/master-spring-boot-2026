package com.project.masterspringboot2026.mapper;

import com.project.masterspringboot2026.dto.request.UserCreationRequest;
import com.project.masterspringboot2026.dto.request.UserUpdateRequest;
import com.project.masterspringboot2026.dto.response.UserResponse;
import com.project.masterspringboot2026.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
