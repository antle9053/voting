package com.example.voting.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
  @SequenceGenerator(name = "accountSeq", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
  long id;

  String username;
  String password;
  String firstname;
  String lastname;
  String email;
  LocalDate dob;
}
