package com.example.voting.service;

import com.example.voting.dto.request.LoginRequest;
import com.example.voting.entity.Account;
import com.example.voting.exception.AppException;
import com.example.voting.exception.ErrorCode;
import com.example.voting.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
  AccountRepository accountRepository;

  public boolean authenticate(LoginRequest loginRequest) {

    Account account =
        accountRepository
            .findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.matches(loginRequest.getPassword(), account.getPassword());
  }
}
