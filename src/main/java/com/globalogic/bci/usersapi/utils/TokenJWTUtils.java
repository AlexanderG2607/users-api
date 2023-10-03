package com.globalogic.bci.usersapi.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class TokenJWTUtils {

    @Autowired
    private JwtBuilder jwtBuilder;

    @Autowired
    private JwtParser jwtParser;

    private static JwtParser _jwtParser;

    private static JwtBuilder _jwtBuilder;

    public TokenJWTUtils(JwtBuilder jwtBuilder, JwtParser jwtParser) {
        this._jwtParser = jwtParser;
        this._jwtBuilder = jwtBuilder;
    }

    public static String generateToken(String email, String password) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("password", password);

        return _jwtBuilder.setClaims(claims).compact();
    }

    public static Optional<Jws<Claims>> validateJWTToken(String token) {
        return Optional.of(_jwtParser.parseClaimsJws(token));
    }
}