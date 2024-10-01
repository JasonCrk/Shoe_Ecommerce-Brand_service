package com.shoe_ecommerce.brand.context.application.commands.follow;

import com.shoe_ecommerce.brand.context.domain.BrandFollower;
import com.shoe_ecommerce.brand.context.domain.ports.repositories.BrandFollowerRepository;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import com.shoe_ecommerce.brand.context.shared.domain.value_objects.UserId;

import com.shoe_ecommerce.brand.shared.domain.Service;

@Service
public final class FollowBrand {

    private final BrandFollowerRepository followRepository;

    public FollowBrand(BrandFollowerRepository followRepository) {
        this.followRepository = followRepository;
    }

    public void follow(UserId userId, BrandId brandId) {
        boolean isNotBrandFollower = !followRepository.existsBy(userId, brandId);

        if (isNotBrandFollower) {
            BrandFollower follow = BrandFollower.create(brandId, userId);
            followRepository.save(follow);
            return;
        }

        followRepository.deleteBy(userId, brandId);
    }
}
