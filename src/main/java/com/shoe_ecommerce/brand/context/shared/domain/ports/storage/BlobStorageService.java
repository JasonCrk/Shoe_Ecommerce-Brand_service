package com.shoe_ecommerce.brand.context.shared.domain.ports.storage;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface BlobStorageService {
    CompletableFuture<Blob> uploadLogo(MediaFile mediaFile);
    CompletableFuture<Blob> uploadBanner(MediaFile mediaFile);
    Map<String, Blob> uploadLogoAndBannerInParallel(
            Optional<MediaFile> logoMediaFile,
            Optional<MediaFile> bannerMediaFile
    );
}
