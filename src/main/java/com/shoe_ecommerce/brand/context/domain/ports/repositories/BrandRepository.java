package com.shoe_ecommerce.brand.context.domain.ports.repositories;

import com.shoe_ecommerce.brand.context.domain.Brand;

public interface BrandRepository {
    Brand save(Brand brand);
}
