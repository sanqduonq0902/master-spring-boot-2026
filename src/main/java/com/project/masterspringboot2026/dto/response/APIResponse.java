package com.project.masterspringboot2026.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse <T> {
    int code;
    String message;
    T result;
}
