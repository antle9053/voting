package com.example.voting.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAccountResponse {
  String id;
  String username;
  String password;
  String firstname;
  String lastname;
  String email;
  String dob;
}
