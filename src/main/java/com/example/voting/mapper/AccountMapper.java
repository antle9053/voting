package com.example.voting.mapper;

import com.example.voting.dto.request.CreateAccountRequest;
import com.example.voting.dto.request.UpdateAccountRequest;
import com.example.voting.dto.response.CreateAccountResponse;
import com.example.voting.dto.response.UpdateAccountResponse;
import com.example.voting.entity.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account toAccount(CreateAccountRequest request);

  @Mapping(target = "password", ignore = true)
  CreateAccountResponse toCreateAccountResponse(Account account);

  @Mapping(target = "password", ignore = true)
  UpdateAccountResponse toUpdateAccountResponse(Account account);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateAccount(@MappingTarget Account account, UpdateAccountRequest request);
}
