package com.example.demo.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

@Service
public class TokenBlacklistService {
    private static final Logger logger = LoggerFactory.getLogger(TokenBlacklistService.class);

    private final Map<String, Date> blacklistedTokens = new ConcurrentHashMap<>();

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Add a token to the blacklist
     * 
     * @param token JWT token to blacklist
     */
    public void blacklistToken(String token) {
        try {
            // Parse the token to get its expiration date
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            blacklistedTokens.put(token, expiration);
            logger.info("Token blacklisted until: {}", expiration);
        } catch (Exception e) {
            logger.error("Error blacklisting token: {}", e.getMessage());
        }
    }

    /**
     * Check if a token is blacklisted
     * 
     * @param token JWT token to check
     * @return true if the token is blacklisted
     */
    public boolean isBlacklisted(String token) {
        return blacklistedTokens.containsKey(token);
    }

    /**
     * Clean up expired tokens from the blacklist
     * Runs every hour by default
     */
    @Scheduled(fixedRate = 3600000) // Run every hour
    public void cleanupExpiredTokens() {
        Date now = new Date();
        int initialSize = blacklistedTokens.size();

        // Remove tokens that have expired
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));

        int removed = initialSize - blacklistedTokens.size();
        if (removed > 0) {
            logger.info("Cleaned up {} expired tokens from blacklist", removed);
        }
    }

    /**
     * Get the current size of the blacklist
     * 
     * @return number of blacklisted tokens
     */
    public int getBlacklistSize() {
        return blacklistedTokens.size();
    }
}