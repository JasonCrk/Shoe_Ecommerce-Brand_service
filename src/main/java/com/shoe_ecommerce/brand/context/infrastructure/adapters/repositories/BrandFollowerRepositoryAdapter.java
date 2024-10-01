package com.shoe_ecommerce.brand.context.infrastructure.adapters.repositories;

import com.shoe_ecommerce.brand.context.domain.BrandFollower;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandFollowerRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.mappers.BrandFollowerMapper;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.repositories.JpaBrandFollowerRepository;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.UserId;

import com.shoe_ecommerce.brand.shared.domain.Service;

import java.util.UUID;

@Service
public final class BrandFollowerRepositoryAdapter implements BrandFollowerRepository {

    private final JpaBrandFollowerRepository repository;

    public BrandFollowerRepositoryAdapter(JpaBrandFollowerRepository repository) {
        this.repository = repository;
    }

    @Override
    public BrandFollower save(BrandFollower follow) {
        return BrandFollowerMapper.toEntity(
                repository.save(BrandFollowerMapper.toModel(follow))
        );
    }

    @Override
    public boolean existsBy(UserId userId, BrandId brandId) {
        return repository.existsBy(UUID.fromString(userId.value()), UUID.fromString(brandId.value()));
    }

    @Override
    public void deleteBy(UserId userId, BrandId brandId) {
        repository.deleteBy(UUID.fromString(userId.value()), UUID.fromString(brandId.value()));
    }
}
