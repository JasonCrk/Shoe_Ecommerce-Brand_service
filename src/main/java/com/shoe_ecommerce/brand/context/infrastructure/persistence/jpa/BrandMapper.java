package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa;

import com.shoe_ecommerce.brand.context.domain.Brand;
import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import java.util.UUID;

public final class BrandMapper {

    public static Brand toEntity(JpaBrand brand) {
        return new Brand(
                new BrandId(brand.getId().toString()),
                new BrandName(brand.getName()),
                new BrandAbout(brand.getAbout()),
                new BrandJoinedAt(brand.getJoinedAt()),
                new BrandLogo(brand.getLogo()),
                new BrandBanner(brand.getBanner())
        );
    }

    public static JpaBrand toModel(Brand brand) {
        return new JpaBrand(
                UUID.fromString(brand.id().value()),
                brand.name().value(),
                brand.joinedAt().value(),
                brand.about().value(),
                brand.logo().value(),
                brand.banner().value()
        );
    }
}
