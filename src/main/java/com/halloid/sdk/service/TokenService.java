package com.halloid.sdk.service;

import com.halloid.sdk.model.AuthenticationResponse;
import com.halloid.sdk.model.JWTBody;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class TokenService {

    private static TokenService tokenService;

    public static TokenService getInstance() {
        if (tokenService == null) {
            tokenService = new TokenService();
        }
        return tokenService;
    }

    public String generateSignedJWT(String clientID, String myPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey privateKey = getPrivateKey(myPrivateKey);

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("clientID", clientID)
                .setSubject(clientID)
                .setAudience("HalloID")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(60, ChronoUnit.MINUTES)))
                .signWith(privateKey)
                .compact();

        return jwtToken;
    }

    public AuthenticationResponse verifyJWTSignature(String halloIdPublicKey, String jwtString)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey publicKey = getPublicKey(halloIdPublicKey);

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(jwtString);
        JWTBody jwtBody = new JWTBody(jwt.getBody().get("clientID", String.class), jwt.getBody().get("sub", String.class), jwt.getBody().get("aud", String.class),
                jwt.getBody().get("jti", String.class), jwt.getBody().get("iat", Integer.class), jwt.getBody().get("exp", Integer.class));
        return new AuthenticationResponse(jwtString, true, true, jwt.getBody().get("username", String.class), 3600L, jwtBody);

    }

    /**
     * RSA PRIVATE KEY must be provided in PKCS #8 format. Do openssl pkcs8 -topk8 to convert a private key from
     * traditional format to pkcs#8 format. The key also must be a valid BASE64 format.
     * @param rsaPrivateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PrivateKey getPrivateKey(String rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");
        rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "");

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }

    private static PublicKey getPublicKey(String rsaPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        rsaPublicKey = rsaPublicKey.replace("-----BEGIN PUBLIC KEY-----", "");
        rsaPublicKey = rsaPublicKey.replace("-----END PUBLIC KEY-----", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(rsaPublicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

}
