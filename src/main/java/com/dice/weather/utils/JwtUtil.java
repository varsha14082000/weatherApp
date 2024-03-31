package com.dice.weather.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

    @Slf4j
    @Component
    public  class JwtUtil {
        @Value("${secret}")
        private String secret;

        private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * 10;

        public String generateToken(UserDetails userDetails) {
            Map<String,Object> claims  = new HashMap<>();

            return doGenerateToken(claims,userDetails.getUsername());
        }

        private String doGenerateToken(Map<String ,Object> claims, String subject) {
            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS256, secret).compact();
        }



        public Claims getAllClaimsFromToken(String token) {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }
        public Date getExpirationDateFromToken(String token) {
            return getAllClaimsFromToken(token).getExpiration();
        }
        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        public String getUserNameFromToken(String token) {
            return getClaimFromToken(token,Claims::getSubject);
        }

        public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }



        public boolean validateToken(String token) {
            return !isTokenExpired(token);
        }
    }

