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
    private String halloIDPublicKey;
    private TokenService tokenService;

    public HalloIDJavaSDK(String clientID, String privateKey, String halloIDPublicKey) {
        this.clientID = clientID;
        this.privateKey = privateKey;
        this.halloIDPublicKey = halloIDPublicKey;
        this.tokenService = TokenService.getInstance();
    }

    public String generateServiceToken() {
//        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getBytes());

        String privateKeyBase64 = this.sanitizeKey(privateKey);
        try {
            return tokenService.generateSignedJWT(clientID, privateKeyBase64);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InvalidTokenKeyException("Can't generate the token with given parameters");
        }
    }

    public AuthenticationResponse validateJWT(String token) {
        String publicKeyBase64 = this.sanitizeKey(halloIDPublicKey);
        try {
            return tokenService.verifyJWTSignature(publicKeyBase64, token);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InvalidTokenSignatureException("Signature cannot be verified for given token");
        }
    }

    private String sanitizeKey(String key) {
        key = key.replace("-----BEGIN PRIVATE KEY-----", "");
        key = key.replace("-----END PRIVATE KEY-----", "");
        key = key.replace("-----BEGIN PUBLIC KEY-----", "");
        key = key.replace("-----END PUBLIC KEY-----", "");
        key = key.replace("-", "");
        key = key.replace("\n", "").replace("\r", "");
        return key;
    }
}
