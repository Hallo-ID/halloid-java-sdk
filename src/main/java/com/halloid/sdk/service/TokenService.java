package com.halloid.sdk.service;

public class TokenService {

    private static TokenService tokenService;

    public static TokenService getInstance() {
        if (tokenService == null) {
            tokenService = new TokenService();
        }
        return tokenService;
    }

    public String generateSignedJWT(String clientID, String privateKey) {
        return null;
    }

    public Boolean verifyJWTSignature(String privateKey, String token) {
        return null;
    }

}
