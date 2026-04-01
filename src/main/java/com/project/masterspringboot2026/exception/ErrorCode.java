package com.project.masterspringboot2026.exception;

public enum ErrorCode {
    USER_EXISTED(404, "User existed");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
