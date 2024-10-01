package com.shoe_ecommerce.brand.context.domain.exceptions;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.shared.domain.exceptions.DomainError;

public final class BrandNotExist extends DomainError {
    public BrandNotExist(BrandId id) {
        super("brand_not_exist", String.format("The brand <%s> doesn't exist", id.value()));
    }
}
