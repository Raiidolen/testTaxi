package com.siteecom.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Désactive CSRF pour le développement
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // AUTORISE TOUT LE MONDE SANS LOGIN
                )
                .formLogin(AbstractHttpConfigurer::disable) // Désactive le formulaire de login
                .httpBasic(AbstractHttpConfigurer::disable); // Désactive l'authentification basique

        return http.build();
    }
}