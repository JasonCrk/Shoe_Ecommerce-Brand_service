package com.shoe_ecommerce.brand.context.shared.domain.value_objects;

import com.shoe_ecommerce.brand.shared.domain.value_objects.StringValueObject;

public class ImageUrl extends StringValueObject {

    public ImageUrl(String value) {
        super(ensureIsValid(value));
    }

    private static String ensureIsValid(String value) throws IllegalArgumentException {
        return value;
    }
}
