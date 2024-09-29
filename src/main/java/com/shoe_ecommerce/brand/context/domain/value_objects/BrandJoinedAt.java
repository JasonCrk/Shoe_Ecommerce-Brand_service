package com.shoe_ecommerce.brand.context.domain.value_objects;

import com.shoe_ecommerce.brand.shared.domain.value_objects.DateValueObject;

import java.time.LocalDate;

public final class BrandJoinedAt extends DateValueObject {

    public BrandJoinedAt(LocalDate value) {
        super(value);
    }

    public BrandJoinedAt() {
        super(null);
    }
}
