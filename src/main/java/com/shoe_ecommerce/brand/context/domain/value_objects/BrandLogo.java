package com.shoe_ecommerce.brand.context.domain.value_objects;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.ImageUrlValueObject;

public final class BrandLogo extends ImageUrlValueObject {

    public BrandLogo(String value) {
        super(value);
    }

    public BrandLogo() {
        super(null);
    }
}
