package com.example.voting.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountResponse {
  String id;
  String username;
  String password;
  String firstname;
  String lastname;
  String email;
  String dob;
}
