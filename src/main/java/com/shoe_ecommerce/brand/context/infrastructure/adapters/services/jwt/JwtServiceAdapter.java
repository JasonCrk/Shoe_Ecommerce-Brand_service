package com.shoe_ecommerce.brand.context.infrastructure.adapters.services.jwt;

import com.shoe_ecommerce.brand.context.domain.ports.services.jwt.JwtService;
import com.shoe_ecommerce.brand.context.shared.domain.AuthUser;

import com.shoe_ecommerce.brand.shared.domain.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

@Service
public final class JwtServiceAdapter implements JwtService {

    @Value("")
    private String secretKey;

    @Override
    public AuthUser getUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .decryptWith(getSignInKey())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();

        return new AuthUser(
                claims.get("userId", String.class),
                claims.getSubject());
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
