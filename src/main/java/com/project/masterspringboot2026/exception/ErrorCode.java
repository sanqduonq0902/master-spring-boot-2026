package com.project.masterspringboot2026.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_EXISTED(400, "User existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(400, "Unauthenticated", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(403, "Unauthorized", HttpStatus.FORBIDDEN),
    USER_NOT_EXISTED(404, "User not existed", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatusCode statusCode;

}
