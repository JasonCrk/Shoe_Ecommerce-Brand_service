package com.shoe_ecommerce.brand.context.shared.domain.exceptions;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.shared.domain.exceptions.DomainError;

public class UnauthorizedAssociatedBrand extends DomainError {
    public UnauthorizedAssociatedBrand(BrandId id) {
        super(
                "authorized_associated_brand",
                String.format("The account is not associated with the brand <%s>", id.value())
        );
    }
}
