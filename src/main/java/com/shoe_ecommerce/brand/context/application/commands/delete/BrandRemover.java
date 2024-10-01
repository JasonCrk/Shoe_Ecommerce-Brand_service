package com.shoe_ecommerce.brand.context.application.commands.delete;

import com.shoe_ecommerce.brand.context.domain.exceptions.BrandNotExist;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import com.shoe_ecommerce.brand.shared.domain.Service;

@Service
public final class BrandRemover {

    private final BrandRepository brandRepository;

    public BrandRemover(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void remove(BrandId id) {
        boolean brandNotExists = !brandRepository.existsById(id);

        if (brandNotExists) throw new BrandNotExist(id);

        brandRepository.deleteById(id);
    }
}
