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
    private String jdbcUrl;

    public String generateJWT() {
        return "";
    }
    public static String createJWT(String subject) throws IOException {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        String secrectKey = "D6DC75171EB5449102FBF2C55E3C46655F3D2D651BBFFCF2936E5B43B1B9B436";

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secrectKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) throws IOException {
        String secrectKey = "D6DC75171EB5449102FBF2C55E3C46655F3D2D651BBFFCF2936E5B43B1B9B436";
        //Will has an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secrectKey))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

}
