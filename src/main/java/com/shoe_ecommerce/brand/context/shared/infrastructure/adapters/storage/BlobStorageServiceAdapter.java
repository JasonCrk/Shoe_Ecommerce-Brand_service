package com.shoe_ecommerce.brand.context.shared.infrastructure.adapters.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandBanner;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandLogo;
import com.shoe_ecommerce.brand.context.shared.domain.exceptions.FileUploadFailure;
import com.shoe_ecommerce.brand.context.shared.domain.ports.storage.Blob;
import com.shoe_ecommerce.brand.context.shared.domain.ports.storage.BlobStorageService;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.UuidGenerator;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class BlobStorageServiceAdapter implements BlobStorageService {

    @Value("${spring.cloud.azure.storage.blob.brand-logo-container}")
    public String blobStorageBrandLogoContainer;

    @Value("${spring.cloud.azure.storage.blob.brand-banner-container}")
    public String blobStorageBrandBannerContainer;

    private final BlobServiceClient blobServiceClient;
    private final UuidGenerator uuidGenerator;

    public BlobStorageServiceAdapter(BlobServiceClient blobServiceClient, UuidGenerator uuidGenerator) {
        this.blobServiceClient = blobServiceClient;
        this.uuidGenerator = uuidGenerator;
    }

    @Override
    public CompletableFuture<Blob> uploadLogo(MediaFile mediaFile) {
        try {
            return CompletableFuture.completedFuture(upload(mediaFile, blobStorageBrandLogoContainer));
        } catch (IOException e) {
            return CompletableFuture.failedFuture(
                    new FileUploadFailure("An error occurred while trying to upload the logo image")
            );
        }
    }

    @Override
    public CompletableFuture<Blob> uploadBanner(MediaFile mediaFile) {
        try {
            return CompletableFuture.completedFuture(upload(mediaFile, blobStorageBrandBannerContainer));
        } catch (IOException e) {
            return CompletableFuture.failedFuture(
                    new FileUploadFailure("An error occurred while trying to upload the banner image")
            );
        }
    }

    @Override
    public Map<String, Blob> uploadLogoAndBannerInParallel(
            Optional<MediaFile> logoMediaFile,
            Optional<MediaFile> bannerMediaFile
    ) {
        Optional<CompletableFuture<Blob>> logoUploading = Optional.empty();
        Optional<CompletableFuture<Blob>> bannerUploading = Optional.empty();

        if (logoMediaFile.isPresent())
            logoUploading = Optional.of(uploadLogo(logoMediaFile.get()));

        if (bannerMediaFile.isPresent())
            bannerUploading = Optional.of(uploadBanner(bannerMediaFile.get()));

        List<CompletableFuture<Blob>> futureUploadingBlobs = new ArrayList<>();
        logoUploading.ifPresent(futureUploadingBlobs::add);
        bannerUploading.ifPresent(futureUploadingBlobs::add);

        CompletableFuture<Void> uploadingBlobs = futureUploadingBlobs.isEmpty()
                ? CompletableFuture.completedFuture(null)
                : CompletableFuture.allOf(futureUploadingBlobs.toArray(new CompletableFuture[0]));

        Map<String, Blob> fileUploads = new HashMap<>();

        try {
            uploadingBlobs.get();

            if (logoUploading.isPresent()) fileUploads.put("logo", logoUploading.get().get());
            if (bannerUploading.isPresent()) fileUploads.put("banner", bannerUploading.get().get());
        } catch (InterruptedException | ExecutionException e) {
            throw new FileUploadFailure(e.getMessage());
        }

        return fileUploads;
    }


    private Blob upload(MediaFile mediaFile, String blobStorageContainer) throws IOException {
        String[] filenameSplit = mediaFile.getOriginalFilename().split("\\.");
        String mediaFileExtension = filenameSplit[filenameSplit.length - 1];

        String blobFilename = uuidGenerator.generate() + "." + mediaFileExtension;

        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(blobStorageContainer)
                .getBlobClient(blobFilename);

        blobClient.upload(mediaFile.getInputStream(), mediaFile.getSize(), true);

        return new Blob(blobClient.getBlobUrl(), blobClient.getBlobName());
    }
}
