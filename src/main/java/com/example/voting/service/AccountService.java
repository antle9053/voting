package com.example.voting.service;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.dto.request.UpdateAccountRequest;
import com.example.voting.dto.response.CreateAccountResponse;
import com.example.voting.dto.response.UpdateAccountResponse;
import com.example.voting.entity.Account;
import com.example.voting.exception.AppException;
import com.example.voting.exception.ErrorCode;
import com.example.voting.mapper.AccountMapper;
import com.example.voting.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
  AccountRepository accountRepository;
  AccountMapper accountMapper;

  public CreateAccountResponse createAccount(CreateAccountRequest request) {
    if (accountRepository.existsByUsername(request.getUsername())) {
      throw new AppException(ErrorCode.USER_EXISTED);
    }

    Account account = accountMapper.toAccount(request);

    account.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));

    return accountMapper.toCreateAccountResponse(accountRepository.save(account));
  }

  public UpdateAccountResponse updateAccount(String userId, UpdateAccountRequest request) {
    Account account = getAccountById(userId);
    accountMapper.updateAccount(account, request);

    return accountMapper.toUpdateAccountResponse(accountRepository.save(account));
  }

  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }

  public Account getAccountById(String id) {
    return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }
}
