package com.halloid.sdk;

import com.halloid.sdk.exception.InvalidTokenKeyException;
import com.halloid.sdk.exception.InvalidTokenSignatureException;
import com.halloid.sdk.model.AuthenticationResponse;
import com.halloid.sdk.service.TokenService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class HalloIDJavaSDK {

    private String clientID;
    private String privateKey;
    private TokenService tokenService;

    public HalloIDJavaSDK(String clientID, String privateKey) {
        this.clientID = clientID;
        this.privateKey = privateKey;
        this.tokenService = TokenService.getInstance();
    }

    public String generateServiceToken() {
        try {
            return tokenService.generateSignedJWT(clientID, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InvalidTokenKeyException("Can't generate the token with given parameters");
        }
    }

    public AuthenticationResponse validateJWT(String token) {
        try {
            return tokenService.verifyJWTSignature(privateKey, token);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InvalidTokenSignatureException("Signature cannot be verified for given token");
        }
    }
}
