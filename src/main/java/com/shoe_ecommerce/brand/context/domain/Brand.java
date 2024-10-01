package com.shoe_ecommerce.brand.context.domain;

import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import java.util.Objects;

public class Brand {
    private final BrandId id;
    private BrandName name;
    private BrandAbout about;
    private BrandLogo logo;
    private BrandBanner banner;
    private final BrandJoinedAt joinedAt;

    public Brand(
            BrandId id,
            BrandName name,
            BrandAbout about,
            BrandJoinedAt joinedAt,
            BrandLogo logo,
            BrandBanner banner
    ) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.joinedAt = joinedAt;
        this.logo = logo;
        this.banner = banner;
    }

    public static Brand create(
            BrandId id,
            BrandName name,
            BrandAbout about,
            BrandLogo logo,
            BrandBanner banner
    ) {
        Brand brand = new Brand(id, name, about, new BrandJoinedAt(), logo, banner);
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

    public BrandLogo logo() {
        return logo;
    }

    public BrandBanner banner() {
        return banner;
    }

    public void updateName(BrandName name) {
        this.name = name;
    }

    public void updateAbout(BrandAbout about) {
        this.about = about;
    }

    public void updateLogo(BrandLogo logo) {
        this.logo = logo;
    }

    public void updateBanner(BrandBanner banner) {
        this.banner = banner;
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
                logo.equals(brand.logo) &&
                banner.equals(brand.banner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, about, joinedAt, logo, banner);
    }
}
