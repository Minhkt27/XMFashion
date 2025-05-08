package com.project.xm.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.xm.dto.request.AuthenticationRequest;
import com.project.xm.dto.request.IntrospectsRequest;
import com.project.xm.dto.respone.AuthenticationResponse;
import com.project.xm.dto.respone.IntrospectsResponse;
import com.project.xm.model.User;
import com.project.xm.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectsResponse introspect(IntrospectsRequest request) throws JOSEException, ParseException {
        var token= request.getToken();
        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT=SignedJWT.parse(token);

         //check Hạn token
        Date expiryTime= signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified=  signedJWT.verify(verifier);

        return  IntrospectsResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user=userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not existed"));

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);

        boolean authenticated= passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(!authenticated)
            throw new RuntimeException("unauthenticated");

        var token=generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user){
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("xm.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();

        Payload payload=new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject=new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        return String.valueOf(user.getRoleId());
    }




}
