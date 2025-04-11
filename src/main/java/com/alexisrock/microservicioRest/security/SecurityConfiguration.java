package com.alexisrock.microservicioRest.security;
import com.alexisrock.microservicioRest.Common.enums.ParamConfig;
import com.alexisrock.microservicioRest.login.domain.interfaces.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private ConfigurationRepository configurationRepository;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        return http
                .securityMatcher("/recordatorio/**") // Asegura que la configuración se aplique a "/recordatorio" y sus subrutas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/login/create").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT es sin estado
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Agrega tu filtro antes del filtro de autenticación estándar
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(null)
                )
                .build();
    }
}
