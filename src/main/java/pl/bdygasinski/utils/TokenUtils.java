package pl.bdygasinski.utils;

import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenUtils {

    //todo change for property
    private static final long expirationTimeInMillis = 1200_000;

    //todo change for system property and change key
    private static final String key = "$3bus>[i-y6e^A<{.:D$WON_*BDz@ooPQ]I~+[Q;UK'+,.-{_o!#~uD$$<i-oR?3e";

    public static String generateToken(String login, String role) {
        long timeInMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setIssuer(login)
                .setIssuedAt(new Date(timeInMillis))
                .setExpiration(new Date(timeInMillis + expirationTimeInMillis))
                .setClaims(Map.of("role", role))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(token).parseClaimsJws(token);
    }


}
