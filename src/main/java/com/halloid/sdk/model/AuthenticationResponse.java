package com.halloid.sdk.model;

public class AuthenticationResponse {
    private String authorizationToken;
    private Boolean tokenVerified;
    private Boolean userVerified;
    private String username;
    private Long timeout;

    public AuthenticationResponse(String authorizationToken, Boolean tokenVerified, Boolean userVerified, String username, Long timeout) {
        this.authorizationToken = authorizationToken;
        this.tokenVerified = tokenVerified;
        this.userVerified = userVerified;
        this.username = username;
        this.timeout = timeout;
    }
}
