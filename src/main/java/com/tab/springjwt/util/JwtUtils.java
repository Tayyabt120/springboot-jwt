package com.tab.springjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TAYYAB
 */
@Service
public class JwtUtils {

    private String SECRET_KEY = "Batman";

    public String extractUserName(String token) {
        return (String) extractClaim(token).getSubject();
    }

    private Claims extractClaim(String token) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
        return claims;
    }

    private boolean isTokenExpired(String token) {
        boolean isExpired = !extractClaim(token).getExpiration().before(new Date());
        return isExpired;
    }

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        boolean isValid = username.equals(userDetails.getUsername()) && isTokenExpired(token);
        return isValid;
    }

}
