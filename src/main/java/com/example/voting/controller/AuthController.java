package com.example.voting.controller;

import com.example.voting.dto.request.LoginRequest;
import com.example.voting.dto.response.ApiResponse;
import com.example.voting.dto.response.LoginResponse;
import com.example.voting.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
  AuthService authService;

  @PostMapping("/login")
  ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    boolean result = authService.authenticate(loginRequest);
    return ApiResponse.<LoginResponse>builder()
        .code(200)
        .result(LoginResponse.builder().authenticated(result).build())
        .build();
  }
}
