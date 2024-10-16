package com.shoe_ecommerce.brand.context.application.commands.check;

import com.shoe_ecommerce.brand.context.domain.exceptions.BrandNotExist;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandHandler;

@Service
public final class CheckBrandExistsCommandHandler implements CommandHandler<CheckBrandExistsCommand, Void> {

    private final BrandRepository brandRepository;

    public CheckBrandExistsCommandHandler(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Void handle(CheckBrandExistsCommand command) {
        BrandId brandId = new BrandId(command.brandId());
        boolean brandNotExist = !brandRepository.existsById(brandId);

        if (brandNotExist) throw new BrandNotExist(brandId);

        return null;
    }
}
