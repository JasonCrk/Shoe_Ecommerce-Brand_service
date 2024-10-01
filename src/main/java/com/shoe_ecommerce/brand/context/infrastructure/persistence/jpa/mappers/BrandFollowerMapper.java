package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.mappers;

import com.shoe_ecommerce.brand.context.domain.BrandFollower;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models.JpaBrandFollower;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.UserId;

import java.util.UUID;

public final class BrandFollowerMapper {

    public static BrandFollower toEntity(JpaBrandFollower follower) {
        return new BrandFollower(
                new BrandId(follower.getBrandId().toString()),
                new UserId(follower.getUserId().toString())
        );
    }

    public static JpaBrandFollower toModel(BrandFollower follower) {
        return new JpaBrandFollower(
                UUID.fromString(follower.brandId().value()),
                UUID.fromString(follower.userId().value())
        );
    }
}
