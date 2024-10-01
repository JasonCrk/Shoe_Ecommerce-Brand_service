package com.shoe_ecommerce.brand.context.domain;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.context.shared.domain.value_objects.UserId;

import java.util.Objects;

public final class BrandFollower {
    private final BrandId brandId;
    private final UserId userId;

    public BrandFollower(BrandId brandId, UserId userId) {
        this.brandId = brandId;
        this.userId = userId;
    }

    public static BrandFollower create(BrandId brandId, UserId userId) {
        BrandFollower follow = new BrandFollower(brandId, userId);
        return follow;
    }

    public BrandId brandId() {
        return brandId;
    }

    public UserId userId() {
        return userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        BrandFollower that = (BrandFollower) object;
        return brandId.equals(that.brandId) &&
                userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, userId);
    }
}
