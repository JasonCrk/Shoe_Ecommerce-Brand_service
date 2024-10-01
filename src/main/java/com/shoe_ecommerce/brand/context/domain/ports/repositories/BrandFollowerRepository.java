package com.shoe_ecommerce.brand.context.domain.ports.repositories;

import com.shoe_ecommerce.brand.context.domain.BrandFollower;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.UserId;

public interface BrandFollowerRepository {
    BrandFollower save(BrandFollower follow);
    boolean existsBy(UserId userId, BrandId brandId);
    void deleteBy(UserId userId, BrandId brandId);
}
