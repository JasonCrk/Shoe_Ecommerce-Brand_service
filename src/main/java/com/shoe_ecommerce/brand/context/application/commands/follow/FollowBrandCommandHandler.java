package com.shoe_ecommerce.brand.context.application.commands.follow;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.context.shared.domain.value_objects.UserId;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandHandler;

@Service
public final class FollowBrandCommandHandler implements CommandHandler<FollowBrandCommand, Void> {

    private final FollowBrand followBrand;

    public FollowBrandCommandHandler(FollowBrand followBrand) {
        this.followBrand = followBrand;
    }

    @Override
    public Void handle(FollowBrandCommand command) {
        this.followBrand.follow(new UserId(command.userId()), new BrandId(command.brandId()));
        return null;
    }
}
