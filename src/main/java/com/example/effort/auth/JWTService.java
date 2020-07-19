package com.example.effort.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTService {
    private static final int ONE_DAY_IN_MILLIS = 86_400_000;
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    @PostConstruct
    private void initKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    public String generateToken(Long id, String username) {
        return JWT.create()
                .withClaim("id", id)
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + ONE_DAY_IN_MILLIS))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

    public String validateToken(String jwt) throws JWTVerificationException {
        String encodedPayload = JWT
                .require(Algorithm.RSA256(publicKey, privateKey))
                .build()
                .verify(jwt)
                .getPayload();
        return new String(Base64.getDecoder().decode(encodedPayload));
    }

}
