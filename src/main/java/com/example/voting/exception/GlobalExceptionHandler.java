package com.example.voting.exception;

import com.example.voting.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = RuntimeException.class)
  ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
    System.out.println(exception);
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setCode(ErrorCode.UNCAUGHT_EXCEPTION.getCode());
    apiResponse.setMessage(ErrorCode.UNCAUGHT_EXCEPTION.getMessage());
    return ResponseEntity.badRequest().body(apiResponse);
  }

  @ExceptionHandler(value = AppException.class)
  ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
    ErrorCode errorCode = exception.getErrorCode();
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setCode(errorCode.getCode());
    apiResponse.setMessage(errorCode.getMessage());
    return ResponseEntity.badRequest().body(apiResponse);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    String enumKey = exception.getFieldError().getDefaultMessage();
    ErrorCode errorCode = ErrorCode.INVALID_CODE;

    try {
      errorCode = ErrorCode.valueOf(enumKey);
    } catch (IllegalArgumentException error) {

    }

    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setCode(errorCode.getCode());
    apiResponse.setMessage(errorCode.getMessage());
    return ResponseEntity.badRequest().body(apiResponse);
  }
}
