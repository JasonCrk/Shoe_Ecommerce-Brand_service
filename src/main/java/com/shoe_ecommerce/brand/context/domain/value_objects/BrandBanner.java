package com.shoe_ecommerce.brand.context.domain.value_objects;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.ImageUrl;

public final class BrandBanner extends ImageUrl {

    public BrandBanner(String value) {
        super(value);
    }

    public BrandBanner() {
        super(null);
    }
}
