package com.shoe_ecommerce.brand.context.domain.ports.services.jwt;

import com.shoe_ecommerce.brand.context.shared.domain.AuthUser;

public interface JwtService {
    AuthUser getUserFromToken(String token);
}
