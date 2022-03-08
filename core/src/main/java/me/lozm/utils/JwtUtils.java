package me.lozm.utils;

import io.jsonwebtoken.*;

import java.util.Map;

import static java.lang.String.format;

public class JwtUtils {

    public static Jwt getJwtObject(String jwt, String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parse(jwt.replace("Bearer", ""));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    format("getJwtObject Exception => JWT: %s, signingKey: %s", jwt, signingKey));
        }
    }

    public static Long getUserIdFromJwt(Jwt jwt) {
        Map<String, Object> jwtBody = (Map<String, Object>) jwt.getBody();
        return Long.valueOf((String) jwtBody.get("sub"));
    }

}
