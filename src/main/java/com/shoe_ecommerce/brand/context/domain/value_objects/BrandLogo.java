package com.shoe_ecommerce.brand.context.domain.value_objects;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.ImageUrl;

public final class BrandLogo extends ImageUrl {

    public BrandLogo(String value) {
        super(value);
    }

    public BrandLogo() {
        super(null);
    }
}
