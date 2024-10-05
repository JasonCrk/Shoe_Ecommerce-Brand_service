package com.shoe_ecommerce.brand.context.application.commands.update;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.exceptions.BrandNotExist;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import com.shoe_ecommerce.brand.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;
import com.shoe_ecommerce.brand.context.shared.domain.ports.storage.BlobStorageService;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.Service;

import java.util.Optional;

@Service
public final class BrandEditor {

    private final BrandRepository brandRepository;
    private final BlobStorageService storageService;

    public BrandEditor(BrandRepository brandRepository, BlobStorageService storageService) {
        this.brandRepository = brandRepository;
        this.storageService = storageService;
    }

    public void edit(
            BrandId id,
            BrandId associatedBrandId,
            BrandName name,
            BrandAbout about,
            Optional<MediaFile> logoMediaFile,
            Optional<MediaFile> bannerMediaFile
    ) {
        if (!associatedBrandId.equals(id)) throw new UnauthorizedAssociatedBrand(id);

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotExist(id));

        if (name.value() != null && !brand.name().equals(name))
            brand.updateName(name);

        if (about.value() != null && !brand.about().equals(about))
            brand.updateAbout(about);

        this.storageService.uploadLogoAndBannerInParallel(logoMediaFile, bannerMediaFile);

        this.brandRepository.save(brand);
    }
}
