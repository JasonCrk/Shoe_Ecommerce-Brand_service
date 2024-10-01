package com.shoe_ecommerce.brand.context.presentation.validations.image_content_type;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ImageContentTypeValidator.class})
public @interface ImageContentType {
    AllowedImageContentType contentType();
    String message() default "Invalid image format";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
