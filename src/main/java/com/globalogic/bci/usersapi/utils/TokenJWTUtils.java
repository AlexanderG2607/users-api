package com.globalogic.bci.usersapi.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenJWTUtils {
    public static String generateToken(String email, String password) {

        String secret = "N1tvNgv7NjTm8x36Nh7bw8AC9I86cTMuB8EV4JgK7pg";

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("password", password);

        long exoiratioinTimeInMillies = System.currentTimeMillis() + 3600000; // 1 hora en milisegundos

        return Jwts.builder()
                .setClaims(claims)
                .setSubject("loginToken")
                .setExpiration(new Date(exoiratioinTimeInMillies))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}