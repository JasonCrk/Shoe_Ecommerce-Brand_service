package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models.JpaBrandFollower;
import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models.JpaBrandFollowerId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaBrandFollowerRepository extends JpaRepository<JpaBrandFollower, JpaBrandFollowerId> {
    @Query("SELECT follow FROM JpaBrandFollower follow WHERE follow.userId = :userId AND follow.brandId = :brandId")
    Optional<JpaBrandFollower> findBy(@Param("userId") UUID userId, @Param("brandId") UUID brandId);

    @Query("SELECT COUNT(follow) > 0 FROM JpaBrandFollower follow WHERE follow.userId = :userId AND follow.brandId = :brandId")
    boolean existsBy(@Param("userId") UUID userId, @Param("brandId") UUID brandId);

    @Modifying
    @Query("SELECT COUNT(follow) > 0 FROM JpaBrandFollower follow WHERE follow.userId = :userId AND follow.brandId = :brandId")
    void deleteBy(@Param("userId") UUID userId, @Param("brandId") UUID brandId);
}
