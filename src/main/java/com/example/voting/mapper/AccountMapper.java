package com.example.voting.mapper;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.dto.request.UpdateAccountRequest;
import com.example.voting.dto.response.CreateAccountResponse;
import com.example.voting.entity.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account toAccount(CreateAccountRequest request);

  CreateAccountResponse toCreateAccountResponse(Account account);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateAccount(@MappingTarget Account account, UpdateAccountRequest request);
}
