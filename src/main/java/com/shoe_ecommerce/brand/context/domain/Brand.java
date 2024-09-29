package com.shoe_ecommerce.brand.context.domain;

import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import java.util.Objects;

public class Brand {
    private final BrandId id;
    private final BrandName name;
    private final BrandAbout about;
    private final BrandJoinedAt joinedAt;
    private final BrandPicture picture;
    private final BrandBanner banner;

    public Brand(
            BrandId id,
            BrandName name,
            BrandAbout about,
            BrandJoinedAt joinedAt,
            BrandPicture picture,
            BrandBanner banner
    ) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.joinedAt = joinedAt;
        this.picture = picture;
        this.banner = banner;
    }

    public static Brand create(
            BrandId id,
            BrandName name,
            BrandAbout about,
            BrandPicture picture,
            BrandBanner banner
    ) {
        Brand brand = new Brand(id, name, about, new BrandJoinedAt(), picture, banner);
        return brand;
    }

    public BrandId id() {
        return id;
    }

    public BrandName name() {
        return name;
    }

    public BrandAbout about() {
        return about;
    }

    public BrandJoinedAt joinedAt() {
        return joinedAt;
    }

    public BrandPicture picture() {
        return picture;
    }

    public BrandBanner banner() {
        return banner;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        Brand brand = (Brand) object;
        return id.equals(brand.id) &&
                name.equals(brand.name) &&
                about.equals(brand.about) &&
                joinedAt.equals(brand.joinedAt) &&
                picture.equals(brand.picture) &&
                banner.equals(brand.banner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, about, joinedAt, picture, banner);
    }
}
