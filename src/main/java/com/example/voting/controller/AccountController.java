package com.example.voting.controller;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.entity.Account;
import com.example.voting.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    Account createAccount(@RequestBody @Valid CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/all")
    List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    Account getAccountById(@PathVariable("id") String id) {
        return accountService.getAccountById(id);
    }
}
