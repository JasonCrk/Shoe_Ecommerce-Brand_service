package com.shoe_ecommerce.brand.context.domain.ports.repositories;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import java.util.Optional;

public interface BrandRepository {
    Brand save(Brand brand);
    Optional<Brand> findById(BrandId id);
    boolean existsById(BrandId id);
    void deleteById(BrandId id);
}
