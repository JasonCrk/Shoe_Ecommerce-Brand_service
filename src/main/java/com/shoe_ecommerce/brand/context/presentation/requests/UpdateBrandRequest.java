package com.shoe_ecommerce.brand.context.presentation.requests;

import com.shoe_ecommerce.brand.context.presentation.validations.image_content_type.AllowedImageContentType;
import com.shoe_ecommerce.brand.context.presentation.validations.image_content_type.ImageContentType;
import com.shoe_ecommerce.brand.context.presentation.validations.max_file_size.MaxFileSize;

import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public record UpdateBrandRequest(
        @Size(max = 100, min = 1, message = "Maximum 100 characters")
        String name,

        @Size(max = 65535, message = "Maximum 65535 characters")
        String about,

        @ImageContentType(contentType = AllowedImageContentType.PNG)
        @MaxFileSize(maxMegabytes = 4)
        MultipartFile logo,

        @ImageContentType(contentType = AllowedImageContentType.PNG)
        @MaxFileSize(maxMegabytes = 6)
        MultipartFile banner
) {
}
