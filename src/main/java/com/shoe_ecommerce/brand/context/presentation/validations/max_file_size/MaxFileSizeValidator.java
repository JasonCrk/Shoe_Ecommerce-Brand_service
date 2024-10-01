package com.shoe_ecommerce.brand.context.presentation.validations.max_file_size;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public final class MaxFileSizeValidator implements ConstraintValidator<MaxFileSize, MultipartFile> {

    private int maxMegabytes;

    @Override
    public void initialize(MaxFileSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.maxMegabytes = constraintAnnotation.maxMegabytes();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) return true;

        if (multipartFile.getSize() > maxMegabytes * 1048576L) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("File size must be " + maxMegabytes + "MB or less")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
