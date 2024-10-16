package com.shoe_ecommerce.brand.context.shared.domain.exceptions;

import com.shoe_ecommerce.brand.shared.domain.exceptions.DomainError;

public class FileUploadFailure extends DomainError {
    public FileUploadFailure(String message) {
        super("file_upload_failure", message);
    }
}