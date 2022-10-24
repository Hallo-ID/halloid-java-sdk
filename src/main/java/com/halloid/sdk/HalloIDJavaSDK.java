package com.halloid.sdk;

import com.halloid.sdk.service.TokenService;

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
        return tokenService.generateSignedJWT(clientID, privateKey);
    }

    public Boolean validateJWT(String token) {
        return tokenService.verifyJWTSignature(privateKey, token);
    }
}
