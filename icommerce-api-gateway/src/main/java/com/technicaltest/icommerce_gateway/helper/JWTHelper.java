package com.technicaltest.icommerce_gateway.helper;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Date;

public class JWTHelper {
    @Value( "${jwt.secret}" )
    private static String secrectKey;

    public String generateJWT() {
        return "";
    }
    public static String createJWT(String subject) throws IOException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secrectKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) throws IOException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secrectKey))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean isValidToken(String jwt) throws IOException {
        Claims claims = decodeJWT(jwt);
        if (claims == null) {
            return false;
        }
        String subject = claims.getSubject();
        String expectedToken = createJWT(subject);
        if (jwt.equals(expectedToken)) {
            return true;
        }
        return false;
    }

}
