package com.example.voting.service;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.dto.request.UpdateAccountRequest;
import com.example.voting.dto.response.CreateAccountResponse;
import com.example.voting.entity.Account;
import com.example.voting.exception.AppException;
import com.example.voting.exception.ErrorCode;
import com.example.voting.mapper.AccountMapper;
import com.example.voting.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
  @Autowired AccountRepository accountRepository;
  @Autowired AccountMapper accountMapper;

  public CreateAccountResponse createAccount(CreateAccountRequest request) {
    if (accountRepository.existsByUsername(request.getUsername())) {
      throw new AppException(ErrorCode.USER_EXISTED);
    }

    Account account = accountMapper.toAccount(request);

    return accountMapper.toCreateAccountResponse(accountRepository.save(account));
  }

  public Account updateAccount(String userId, UpdateAccountRequest request) {
    Account account = getAccountById(userId);
    accountMapper.updateAccount(account, request);

    return accountRepository.save(account);
  }

  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }

  public Account getAccountById(String id) {
    return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }
}
