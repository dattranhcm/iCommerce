package com.technicaltest.icommerce_gateway.helper;


import com.technicaltest.icommerce_gateway.client.CustomerServiceClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Date;

@Component
public class JWTHelper {

    @Value( "${jwt.secret}" )
    private String secrectKey;

    public String generateJWT() {
        return "";
    }
    public String createJWT(String subject) throws IOException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secrectKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public Claims decodeJWT(String jwt) throws IOException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secrectKey))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
    private final Logger logger = LoggerFactory.getLogger(JWTHelper.class);
    public Boolean isValidToken(String jwt) throws IOException {
        logger.info("JWTHelper token input: " + jwt);
        Claims claims = decodeJWT(jwt);
        logger.info("JWTHelper claims: " + claims);
        if (claims == null) {
            return false;
        }
        String subject = claims.getSubject();
        String expectedToken = createJWT(subject);
        logger.info("JWTHelper expectedToken:" + expectedToken);
        if (jwt.equals(expectedToken)) {
            return true;
        }
        return false;
    }

}
