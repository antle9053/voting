package com.example.voting.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAccountRequest {
  String username;
  String password;
  String email;
  String dob;
  String firstName;
  String lastName;
}
