package com.example.voting.service;

import com.example.voting.dto.request.IntrospectRequest;
import com.example.voting.dto.request.LoginRequest;
import com.example.voting.dto.response.IntrospectResponse;
import com.example.voting.dto.response.LoginResponse;
import com.example.voting.entity.Account;
import com.example.voting.exception.AppException;
import com.example.voting.exception.ErrorCode;
import com.example.voting.repository.AccountRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
  private static final Logger log = LoggerFactory.getLogger(AuthService.class);
  AccountRepository accountRepository;

  protected static final String SIGNER_KEY = "k7oaRdJUVxT63r7TiOgbUVew7ysBXuuY";

  @SneakyThrows
  public LoginResponse login(LoginRequest loginRequest) {

    Account account =
        accountRepository
            .findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    boolean authenticated = encoder.matches(loginRequest.getPassword(), account.getPassword());

    if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

    String token = generateToken(loginRequest.getUsername());

    return LoginResponse.builder().token(token).authenticated(true).build();
  }

  @SneakyThrows
  public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
    String token = introspectRequest.getToken();

    JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

    SignedJWT signedJWT = SignedJWT.parse(token);

    Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

    boolean isVerified = signedJWT.verify(verifier);

    boolean isValid = isVerified && expiryTime.after(new Date());

    return IntrospectResponse.builder().isValid(isValid).build();
  }

  private String generateToken(String username) throws KeyLengthException {
    JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
    JWTClaimsSet jwtClaimSet =
        new JWTClaimsSet.Builder()
            .subject(username)
            .issuer("anthony")
            .issueTime(new Date())
            .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
            .build();

    Payload payload = new Payload(jwtClaimSet.toJSONObject());

    JWSObject jwsObject = new JWSObject(jwsHeader, payload);

    try {
      jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
      return jwsObject.serialize();
    } catch (JOSEException e) {
      log.error("Cannot create token", e);
      throw new RuntimeException(e);
    }
  }
}
