package com.tab.springjwt.model;

import java.util.Date;

/**
 * @author TAYYAB
 */
public class AuthenticationResponse {

    private String jwtToken;

    private Date expireAt;

    public AuthenticationResponse(String jwtToken, Date expireAt) {
        this.jwtToken = jwtToken;
        this.expireAt = expireAt;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
}
