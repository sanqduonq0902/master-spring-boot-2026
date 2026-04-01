package com.project.masterspringboot2026.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

    String firstName;
    String lastName;
    LocalDate dob;
}
