package com.example.voting.exception;

public enum ErrorCode {
  UNCAUGHT_EXCEPTION(1, "Uncaught exception"),
  INVALID_CODE(2, "Invalid code"),
  USER_EXISTED(409, "User already existed"),
  USERNAME_INVALID(400, "Username is invalid"),
  USERNAME_REQUIRED(400, "Username is required"),
  PASSWORD_INVALID(400, "Password is invalid"),
  PASSWORD_REQUIRED(400, "Password is required"),
  USER_NOT_FOUND(404, "User not found"),
  UNAUTHENTICATED(403, "Unauthenticated"),
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
