package com.example.voting.exception;

public enum ErrorCode {
    UNCAUGHT_EXCEPTION(1, "Uncaught exception"),
    USER_EXISTED(201, "User already existed")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
