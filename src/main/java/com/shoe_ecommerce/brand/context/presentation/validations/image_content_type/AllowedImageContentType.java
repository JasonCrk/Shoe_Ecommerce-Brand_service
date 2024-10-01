package com.shoe_ecommerce.brand.context.presentation.validations.image_content_type;

import lombok.Getter;

@Getter
public enum AllowedImageContentType {
    PNG("image/png");

    private final String mimeType;

    AllowedImageContentType(String mimeType) {
        this.mimeType = mimeType;
    }
}
