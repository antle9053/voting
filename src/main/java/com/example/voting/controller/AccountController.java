package com.example.voting.controller;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.dto.request.UpdateAccountRequest;
import com.example.voting.dto.response.ApiResponse;
import com.example.voting.dto.response.CreateAccountResponse;
import com.example.voting.dto.response.UpdateAccountResponse;
import com.example.voting.entity.Account;
import com.example.voting.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
  @Autowired private AccountService accountService;

  @PostMapping("/create")
  ApiResponse<Account> createAccount(@RequestBody @Valid CreateAccountRequest request) {
    ApiResponse<Account> createAccountResponse = new ApiResponse<>();

    createAccountResponse.setCode(201);
    createAccountResponse.setMessage("Create account successful");
    createAccountResponse.setResult(accountService.createAccount(request));
    return createAccountResponse;
  }

  @PatchMapping("/update/{userId}")
  ApiResponse<UpdateAccountResponse> updateAccount(
      @PathVariable String userId, @RequestBody UpdateAccountRequest request) {
    ApiResponse<UpdateAccountResponse> updateAccountResponse = new ApiResponse<>();
    updateAccountResponse.setCode(204);
    updateAccountResponse.setMessage("Update account successful");
    updateAccountResponse.setResult(accountService.updateAccount(userId, request));
    return updateAccountResponse;
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
