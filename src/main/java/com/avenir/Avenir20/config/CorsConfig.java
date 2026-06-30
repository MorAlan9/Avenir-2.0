package com.avenir.Avenir20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitimos que pase el token y las credenciales
        config.setAllowCredentials(true);

        // La URL exacta de tu React (
        config.addAllowedOrigin("http://localhost:5173/");

        // Permitimos todos los headers (Authorization, Content-Type, etc)
        config.addAllowedHeader("*");

        // Permitimos todos los métodos (GET, POST, PUT, DELETE, OPTIONS, PATCH)
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}