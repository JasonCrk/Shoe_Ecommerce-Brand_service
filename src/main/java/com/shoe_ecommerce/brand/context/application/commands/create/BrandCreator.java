package com.shoe_ecommerce.brand.context.application.commands.create;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import com.shoe_ecommerce.brand.context.shared.domain.ports.storage.BlobStorageService;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.Service;

import java.util.Optional;

@Service
public final class BrandCreator {

    private final BrandRepository brandRepository;
    private final BlobStorageService storageService;

    public BrandCreator(BrandRepository brandRepository, BlobStorageService storageService) {
        this.brandRepository = brandRepository;
        this.storageService = storageService;
    }

    public void create(
            BrandId id,
            BrandName name,
            BrandAbout about,
            Optional<MediaFile> logoMediaFile,
            Optional<MediaFile> bannerMediaFile
    ) {
        BrandBanner uploadedBanner = new BrandBanner();
        BrandLogo uploadedLogo = new BrandLogo();

        var fileUploads = this.storageService.uploadLogoAndBannerInParallel(logoMediaFile, bannerMediaFile);

        if (fileUploads.containsKey("logo"))
            uploadedLogo = new BrandLogo(fileUploads.get("logo").blobUrl());

        if (fileUploads.containsKey("banner"))
            uploadedBanner = new BrandBanner(fileUploads.get("banner").blobUrl());

        Brand brand = Brand.create(id, name, about, uploadedLogo, uploadedBanner);
        this.brandRepository.save(brand);
    }
}
