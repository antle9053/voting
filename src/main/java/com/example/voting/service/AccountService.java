package com.example.voting.service;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.entity.Account;
import com.example.voting.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(CreateAccountRequest request) {
        Account account = new Account();

        if(accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User existed");
        }

        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());
        account.setEmail(request.getEmail());
        account.setFirstname(request.getFirstname());
        account.setLastname(request.getLastname());
        account.setDob(request.getDob());

        return accountRepository.save(account);
    };

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(String id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
