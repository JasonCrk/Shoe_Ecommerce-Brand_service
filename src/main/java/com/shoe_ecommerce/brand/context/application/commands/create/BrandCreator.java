package com.shoe_ecommerce.brand.context.application.commands.create;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.ports.services.storage.BlobStorageService;
import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.query.QueryHandlerExecutionError;

import java.io.IOException;

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
            MediaFile logoMediaFile,
            MediaFile bannerMediaFile
    ) {
        BrandBanner banner = new BrandBanner();
        BrandLogo logo = new BrandLogo();

        if (logoMediaFile != null) {
            try {
                logo = new BrandLogo(this.storageService.uploadLogo(logoMediaFile).blobUrl());
            } catch (IOException e) {
                throw new QueryHandlerExecutionError(e);
            }
        }

        if (bannerMediaFile != null) {
            try {
                banner = new BrandBanner(this.storageService.uploadBanner(bannerMediaFile).blobUrl());
            } catch (IOException e) {
                throw new QueryHandlerExecutionError(e);
            }
        }

        Brand brand = Brand.create(id, name, about, logo, banner);
        this.brandRepository.save(brand);
    }
}
