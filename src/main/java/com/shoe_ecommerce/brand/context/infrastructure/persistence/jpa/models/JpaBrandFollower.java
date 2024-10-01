package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "brand_followers")
@IdClass(JpaBrandFollowerId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class JpaBrandFollower {

    @Id
    @Column(name = "brand_id")
    private UUID brandId;

    @Id
    @Column(name = "user_id")
    private UUID userId;
}
