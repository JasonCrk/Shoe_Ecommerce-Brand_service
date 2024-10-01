package com.shoe_ecommerce.brand.context.infrastructure.adapters.repositories;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.mappers.BrandMapper;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.repositories.JpaBrandRepository;

import com.shoe_ecommerce.brand.shared.domain.Service;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Brand> findById(BrandId id) {
        return repository.findById(UUID.fromString(id.value())).map(BrandMapper::toEntity);
    }

    @Override
    public boolean existsById(BrandId id) {
        return repository.existsById(UUID.fromString(id.value()));
    }

    @Override
    public void deleteById(BrandId id) {
        repository.deleteById(UUID.fromString(id.value()));
    }
}
