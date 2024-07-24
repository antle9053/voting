package com.example.voting.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountResponse {
  long id;
  String username;
  String password;
  String firstname;
  String lastname;
  String email;
  String dob;
}
