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

    RSAPrivateKey privateKey;
    RSAPublicKey publicKey;
    int expirationTime = 1800000;

    @PostConstruct
    private void initKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    public String generateToken(String username) {
        return JWT.create()
                .withClaim("user", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
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
