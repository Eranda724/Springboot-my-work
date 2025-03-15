package com.example.demo.config;

import com.example.demo.Service.TokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final TokenBlacklistService blacklistService;

    public JwtTokenFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService,
            TokenBlacklistService blacklistService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.blacklistService = blacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");

            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);

                // Check if token is blacklisted
                if (blacklistService.isBlacklisted(token)) {
                    logger.warn("Attempt to use blacklisted token");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has been invalidated");
                    return;
                }

                // Validate token before extracting username
                if (jwtUtils.validateToken(token)) {
                    String username = jwtUtils.getUsernameFromToken(token);

                    // Only authenticate if SecurityContext is empty
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        if (userDetails != null) { // Avoid NullPointerException
                            var auth = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());

                            // Add request details (optional)
                            // auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                            SecurityContextHolder.getContext().setAuthentication(auth);
                            logger.debug("User '{}' authenticated successfully", username);
                        }
                    }
                } else {
                    logger.warn("Invalid JWT token");
                }
            } else {
                logger.debug("No Authorization header or bearer token found");
            }
        } catch (Exception e) {
            logger.error("JWT Authentication failed: {}", e.getMessage());
            // Explicitly deny access if token is invalid
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Exclude specific paths from JWT authentication
     * Optional - only needed if you want to exclude paths beyond what's configured
     * in SecurityConfig
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // Skip JWT filter for auth endpoints
        return path.startsWith("/api/auth/login") || path.startsWith("/api/auth/register");
    }
}