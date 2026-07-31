package com.avenir.Avenir20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        // 🌟 LA CLAVE DE TODO: Permitir consultas previas (CORS Preflight)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 🔓 RUTAS PÚBLICAS (Login, Permisos y REGISTRO PÚBLICO)
                        .requestMatchers("/api/usuarios/login", "/api/roles/permisos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        // ⏰ HABILITAMOS MÉTODOS DE MODIFICACIÓN DE HORARIOS PARA AUTENTICADOS
                        .requestMatchers(HttpMethod.PATCH, "/api/horas/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/horas/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/horas/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/horas/**").authenticated()

                        // 🔒 Todo lo demás requiere estar autenticado
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}