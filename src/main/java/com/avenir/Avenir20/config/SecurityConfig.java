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
                        // 🌟 Permitir consultas previas CORS (Preflight)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 🔓 RUTAS PÚBLICAS Y MANEJO DE ERRORES DEL SISTEMA
                        .requestMatchers("/api/usuarios/login", "/api/roles/permisos", "/error").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        // ⏰ HORARIOS
                        .requestMatchers("/api/horas/**").authenticated()

                        // ⚠️ MÓDULOS DE PARÁMETROS IPER
                        .requestMatchers(
                                "/api/tipo-riesgo/**",
                                "/api/categoria-riesgo/**",
                                "/api/causa-riesgo/**",
                                "/api/estado/**",
                                "/api/probabilidad-prioridad/**"
                        ).authenticated()

                        // 🔒 Todo lo demás requiere estar autenticado
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}