    package com.project.masterspringboot2026.dto.response;

    import com.project.masterspringboot2026.entity.Role;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.time.LocalDate;
    import java.util.Set;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class UserResponse {
        String id;
        String username;
        String firstName;
        String lastName;
        LocalDate dob;
        Set<RoleResponse> roles;
    }
