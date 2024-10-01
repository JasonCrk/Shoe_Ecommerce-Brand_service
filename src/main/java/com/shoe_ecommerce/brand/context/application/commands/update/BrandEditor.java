package com.shoe_ecommerce.brand.context.application.commands.update;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.exceptions.BrandNotExist;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.ports.services.storage.BlobStorageService;
import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandHandlerExecutionError;

import java.io.IOException;
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
            BrandName name,
            BrandAbout about,
            MediaFile logo,
            MediaFile banner
    ) {
        Optional<Brand> foundBrand = brandRepository.findById(id);

        if (foundBrand.isEmpty()) throw new BrandNotExist(id);

        Brand brand = foundBrand.get();

        if (name.value() != null && !brand.name().equals(name))
            brand.updateName(name);

        if (about.value() != null && !brand.about().equals(about))
            brand.updateAbout(about);

        if (logo != null) {
            try {
                String logoUrl = this.storageService.uploadLogo(logo).blobUrl();
                brand.updateLogo(new BrandLogo(logoUrl));
            } catch (IOException e) {
                throw new CommandHandlerExecutionError(e);
            }
        }

        if (banner != null) {
            try {
                String bannerUrl = this.storageService.uploadBanner(banner).blobUrl();
                brand.updateBanner(new BrandBanner(bannerUrl));
            } catch (IOException e) {
                throw new CommandHandlerExecutionError(e);
            }
        }

        this.brandRepository.save(brand);
    }
}
