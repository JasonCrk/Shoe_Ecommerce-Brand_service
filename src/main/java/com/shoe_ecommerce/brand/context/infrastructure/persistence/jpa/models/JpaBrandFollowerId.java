package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
public final class JpaBrandFollowerId implements Serializable {
    private UUID brandId;
    private UUID userId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        JpaBrandFollowerId that = (JpaBrandFollowerId) object;
        return brandId.equals(that.brandId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, userId);
    }
}
