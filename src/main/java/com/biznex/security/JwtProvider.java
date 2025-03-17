package com.biznex.security;

import com.biznex.model.role.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long accessExpireTime;

    @Value("${jwt.refresh.expired}")
    private Long refreshExpireTime;

    public String generateAccessToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpireTime))
                .claim("role", role.getName())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String generateRefreshToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpireTime))
                .claim("role", role.getName())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT signature");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        } catch (Exception e) {
            System.out.println("Something went wrong with JWT");
        }
        return null;
    }
}
