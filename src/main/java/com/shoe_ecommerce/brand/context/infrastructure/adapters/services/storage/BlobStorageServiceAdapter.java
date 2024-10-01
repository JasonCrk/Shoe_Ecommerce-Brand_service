package com.shoe_ecommerce.brand.context.infrastructure.adapters.services.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;

import com.shoe_ecommerce.brand.context.domain.ports.services.storage.Blob;
import com.shoe_ecommerce.brand.context.domain.ports.services.storage.BlobStorageService;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.Service;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.UUID;

@Service
public class BlobStorageServiceAdapter implements BlobStorageService {

    @Value("${spring.cloud.azure.storage.blob.brand-logo-container}")
    public String blobStorageBrandLogoContainer;

    @Value("${spring.cloud.azure.storage.blob.brand-banner-container}")
    public String blobStorageBrandBannerContainer;

    private final BlobServiceClient blobServiceClient;

    public BlobStorageServiceAdapter(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    @Override
    public Blob uploadLogo(MediaFile mediaFile) throws IOException {
        return upload(mediaFile, blobStorageBrandLogoContainer);
    }

    @Override
    public Blob uploadBanner(MediaFile mediaFile) throws IOException {
        return upload(mediaFile, blobStorageBrandBannerContainer);
    }

    private Blob upload(MediaFile mediaFile, String blobStorageContainer) throws IOException {
        String[] filenameSplit = mediaFile.getOriginalFilename().split("\\.");
        String mediaFileExtension = filenameSplit[filenameSplit.length - 1];

        String blobFilename = UUID.randomUUID() + "." + mediaFileExtension;

        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(blobStorageContainer)
                .getBlobClient(blobFilename);

        blobClient.upload(mediaFile.getInputStream(), mediaFile.getSize(), true);

        return new Blob(blobClient.getBlobUrl(), blobClient.getBlobName());
    }
}
