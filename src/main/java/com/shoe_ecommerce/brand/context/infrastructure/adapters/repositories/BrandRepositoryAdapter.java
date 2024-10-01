package com.shoe_ecommerce.brand.context.infrastructure.adapters.repositories;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.BrandMapper;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.JpaBrandRepository;

import com.shoe_ecommerce.brand.shared.domain.Service;

@Service
public class BrandRepositoryAdapter implements BrandRepository {

    private final JpaBrandRepository repository;

    public BrandRepositoryAdapter(JpaBrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Brand save(Brand brand) {
        return BrandMapper.toEntity(this.repository.save(BrandMapper.toModel(brand)));
    }
}
