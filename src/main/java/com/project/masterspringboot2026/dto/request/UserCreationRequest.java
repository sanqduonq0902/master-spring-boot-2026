package com.project.masterspringboot2026.dto.request;

import com.project.masterspringboot2026.exception.ErrorCode;
import com.project.masterspringboot2026.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DATE_OF_BIRTH")
    LocalDate dob;
}
