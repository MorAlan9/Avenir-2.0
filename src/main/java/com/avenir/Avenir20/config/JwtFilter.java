package com.avenir.Avenir20.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${security.jwt.secret}")
    private String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                // 💡 Intenta decodificar en Base64; si es un String normal, usa UTF-8
                byte[] keyBytes;
                try {
                    keyBytes = Base64.getDecoder().decode(key);
                } catch (IllegalArgumentException e) {
                    keyBytes = key.getBytes(StandardCharsets.UTF_8);
                }

                Key signingKey = Keys.hmacShaKeyFor(keyBytes);

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(signingKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                String rol = claims.get("rol", String.class);

                @SuppressWarnings("unchecked")
                List<String> permisos = claims.get("permisos", List.class);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    // 1. Cargar permisos específicos
                    if (permisos != null) {
                        permisos.forEach(p -> {
                            if (p != null) authorities.add(new SimpleGrantedAuthority(p.trim()));
                        });
                    }

                    // 2. Si es Admin, asignamos la autoridad maestra
                    if ("ADMINISTRADOR".equalsIgnoreCase(rol)) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
                    }

                    // 3. Asignar también la autoridad dinámica por nombre de rol
                    if (rol != null && !rol.trim().isEmpty()) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.trim().toUpperCase()));
                    }

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                logger.error("Token no válido o fallo en autenticación JWT: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}