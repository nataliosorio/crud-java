package com.sena.crud_hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class corsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Orígenes permitidos
        config.addAllowedOrigin("http://localhost:4200");
        // config.addAllowedOrigin("http://4200"); 
        config.addAllowedOrigin("http://127.0.0.1:57015");

        // Métodos HTTP permitidos
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");

        // Headers permitidos
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");

        // Permitir credenciales
        config.setAllowCredentials(true);

        // Registrar configuración
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
