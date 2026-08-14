package com.avenir.Avenir20.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * Crear un nuevo token simple.
     */
    public String create(String id, String subject) {
        return createConPermisosYRol(id, subject, List.of(), "SIN_ROL");
    }

    /**
     * Crear un token con permisos pero sin rol explícito.
     */
    public String createConPermisos(String id, String subject, List<String> permisos) {
        return createConPermisosYRol(id, subject, permisos, "SIN_ROL");
    }

    /**
     * 🌟 MÉTODOS COMPLETO: Crear token JWT incluyendo Permisos y el Rol.
     */
    public String createConPermisosYRol(String id, String subject, List<String> permisos, String rol) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Key signingKey = Keys.hmacShaKeyFor(DatatypeConverter.parseBase64Binary(key));

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("permisos", permisos)
                .claim("rol", rol) // 👈 Embebemos el rol en las claims
                .signWith(signingKey, signatureAlgorithm);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * Validar y obtener el Subject (email / username) del Token.
     */
    public String getValue(String jwt) {
        Key signingKey = Keys.hmacShaKeyFor(DatatypeConverter.parseBase64Binary(key));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Validar y obtener el ID del usuario guardado en el Token.
     */
    public String getKey(String jwt) {
        Key signingKey = Keys.hmacShaKeyFor(DatatypeConverter.parseBase64Binary(key));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getId();
    }
}