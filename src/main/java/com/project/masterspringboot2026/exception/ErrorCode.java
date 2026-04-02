package com.project.masterspringboot2026.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_EXISTED(400, "User existed"),
    UNAUTHENTICATED(400, "Unauthenticated"),
    USER_NOT_EXISTED(404, "User not existed");

    int code;
    String message;
}
