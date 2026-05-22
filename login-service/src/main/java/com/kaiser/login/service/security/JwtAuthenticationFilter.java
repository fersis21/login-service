package com.kaiser.login.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

/**
 * @author Fernando Apaza
 * Date: 15/05/2026
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // ✅ Log para debugging
        log.debug("Procesando petición: {} {}", request.getMethod(), request.getRequestURI());

        // ✅ Ignorar endpoints públicos
        String path = request.getRequestURI();
        if (path.contains("/auth/login") || path.contains("/auth/register")) {
            log.debug("Endpoint público, continuando sin autenticación");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        log.debug("Authorization header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("No se encontró token Bearer en la petición");
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        log.debug("Token extraído: {}", token.substring(0, Math.min(token.length(), 20)) + "...");

        try {
            if (jwtService.isTokenValid(token)) {
                String username = jwtService.extractUsername(token);
                String rol = jwtService.extractRol(token);
                Long userId = jwtService.extractUserId(token);

                log.info("Token válido para usuario: {}, rol: {}, id: {}", username, rol, userId);

                // ✅ Crear autenticación con el rol
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol))
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                log.warn("Token inválido o expirado");
            }
        } catch (Exception e) {
            log.error("Error validando token: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
