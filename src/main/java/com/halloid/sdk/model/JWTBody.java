package com.halloid.sdk.model;

public class JWTBody {
    private String clientID;
    private String sub;
    private String aud;
    private String jti;
    private Integer iat;
    private Integer exp;

    public JWTBody(String clientID, String sub, String aud, String jti, Integer iat, Integer exp) {
        this.clientID = clientID;
        this.sub = sub;
        this.aud = aud;
        this.jti = jti;
        this.iat = iat;
        this.exp = exp;
    }

    public String getClientID() {
        return clientID;
    }

    public String getSub() {
        return sub;
    }

    public String getAud() {
        return aud;
    }

    public String getJti() {
        return jti;
    }

    public Integer getIat() {
        return iat;
    }

    public Integer getExp() {
        return exp;
    }
}
