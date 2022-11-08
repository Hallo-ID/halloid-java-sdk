package com.halloid.sdk.model;

public class AuthenticationResponse {
    private String authenticationToken;
    private Boolean tokenVerified;
    private Boolean userVerified;
    private String username;
    private Long timeout;
    private JWTBody jwtBody;


    public AuthenticationResponse(String authenticationToken, Boolean tokenVerified, Boolean userVerified, String username, Long timeout, JWTBody jwtBody) {
        this.authenticationToken = authenticationToken;
        this.tokenVerified = tokenVerified;
        this.userVerified = userVerified;
        this.username = username;
        this.timeout = timeout;
        this.jwtBody = jwtBody;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public Boolean getTokenVerified() {
        return tokenVerified;
    }

    public Boolean getUserVerified() {
        return userVerified;
    }

    public String getUsername() {
        return username;
    }

    public Long getTimeout() {
        return timeout;
    }

    public JWTBody getJwtBody() {
        return jwtBody;
    }
}
