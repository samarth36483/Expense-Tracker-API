package com.samarth.expensetrackerapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenUtil {

    private static final long JWT_TOKEN_VALIDITY = 5*60*60;
    Map<String, Object> claims = new HashMap<>();

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String jwtToken){
        return getClaimsFromToken(jwtToken, Claims::getSubject);
    }
    private <T> T getClaimsFromToken(String token, Function<Claims, T> classResolver){
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return classResolver.apply(claims);
    }

    public boolean validateToken(String jwtToken, String userName) {
        final String username = getUsernameFromToken(jwtToken);
        return username.equals(userName) && isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        final Date expiration = getExpirationDateFromToken(jwtToken);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String jwtToken) {
        return getClaimsFromToken(jwtToken, Claims::getExpiration);
    }
}