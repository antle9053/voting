package com.example.voting.dto.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountRequest {
  long id;

  @Size(max = 20, min = 3, message = "USERNAME_INVALID")
  @NotBlank(message = "USERNAME_REQUIRED")
  String username;

  @Size(max = 20, min = 6, message = "PASSWORD_INVALID")
  @NotBlank(message = "PASSWORD_REQUIRED")
  String password;

  String firstname;
  String lastname;
  String email;
  LocalDate dob;
}
