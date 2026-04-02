package com.project.masterspringboot2026.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.masterspringboot2026.dto.request.AuthRequest;
import com.project.masterspringboot2026.dto.request.IntrospectRequest;
import com.project.masterspringboot2026.dto.response.AuthResponse;
import com.project.masterspringboot2026.dto.response.IntrospectResponse;
import com.project.masterspringboot2026.exception.AppException;
import com.project.masterspringboot2026.exception.ErrorCode;
import com.project.masterspringboot2026.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.secret}")
    protected String SECRET_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expTime.after(new Date()))
                .build();
    }

     public AuthResponse authenticate(AuthRequest request) {
         var user = userRepository.findByUsername(request.getUsername())
                 .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

         PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
         boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

         if (!authenticated)
             throw new AppException(ErrorCode.UNAUTHENTICATED);

         var token = generateToken(request.getUsername());

         return AuthResponse.builder()
                 .authenticated(true)
                 .token(token)
                 .build();
     }

     public String generateToken(String username) {
         JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

         JWTClaimsSet claims = new JWTClaimsSet.Builder()
                 .subject(username)
                 .issuer("sanqduonq.com")
                 .issueTime(new Date())
                 .expirationTime(new Date(
                         Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                 ))
                 .build();

         Payload payload = new Payload(claims.toJSONObject());

         JWSObject jwt = new JWSObject(header, payload);

         try {
             jwt.sign(new MACSigner(SECRET_KEY.getBytes()));
             return jwt.serialize();
         } catch (JOSEException e) {
             log.error("Can't generate token");
             throw new RuntimeException(e);
         }
     }
}
