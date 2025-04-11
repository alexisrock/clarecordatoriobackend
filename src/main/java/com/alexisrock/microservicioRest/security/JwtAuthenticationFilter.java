package com.alexisrock.microservicioRest.security;

import com.alexisrock.microservicioRest.Common.enums.ParamConfig;
import com.alexisrock.microservicioRest.login.application.Login.LoginService;
import com.alexisrock.microservicioRest.login.domain.interfaces.ConfigurationRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private LoginService loginService;
    private String Username;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        if (validateToken(jwt)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    Username,
                    null,
                    Collections.emptyList()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(String token) {
        try {
            String secretKey = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtSecretKey.toString()).getValue() != null ? configurationRepository.getValuesConfigurationsById(ParamConfig.JwtSecretKey.toString()).getValue() : "";
            String issuer = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtIssuerToken.toString()).getValue() != null ? configurationRepository.getValuesConfigurationsById(ParamConfig.JwtIssuerToken.toString()).getValue() : "";
            String audience = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtAudienceToken.toString()).getValue() != null ? configurationRepository.getValuesConfigurationsById(ParamConfig.JwtAudienceToken.toString()).getValue() : "";

            if (secretKey.isEmpty() || issuer.isEmpty() || audience.isEmpty()) {
                throw new RuntimeException("Parámetros de configuración JWT incompletos");
            }

            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .requireIssuer(issuer)
                    .requireAudience(audience)
                    .build()
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            if (claims.getExpiration().before(new Date())) {
                return false;
            }
            String username = claims.get("unique_name", String.class);
            if (username == null || username.isEmpty()) {
                return false;
            }
            Username = username;
            return searchUser(username); // tu lógica aquí
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token inválido
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado al validar el token", e);
        }
    }

    private boolean searchUser(String username) {
        String email = loginService.searchEmailUsers(username);
        return email != null; // Ejemplo: cambiar por lógica real
    }
}


