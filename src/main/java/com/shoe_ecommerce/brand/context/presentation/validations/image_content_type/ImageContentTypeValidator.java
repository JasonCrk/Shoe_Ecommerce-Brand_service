package com.shoe_ecommerce.brand.context.presentation.validations.image_content_type;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public final class ImageContentTypeValidator implements ConstraintValidator<ImageContentType, MultipartFile> {

    private AllowedImageContentType contentType;

    @Override
    public void initialize(ImageContentType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        contentType = constraintAnnotation.contentType();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) return true;

        if (!multipartFile.getContentType().equals(contentType.getMimeType())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Image content type is not \"" + contentType.getMimeType() + "\"")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
