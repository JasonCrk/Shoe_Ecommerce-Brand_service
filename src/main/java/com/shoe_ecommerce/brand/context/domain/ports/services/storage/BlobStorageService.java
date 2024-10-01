package com.shoe_ecommerce.brand.context.domain.ports.services.storage;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;

import java.io.IOException;

public interface BlobStorageService {
    Blob uploadLogo(MediaFile mediaFile) throws IOException;
    Blob uploadBanner(MediaFile mediaFile) throws IOException;
}
