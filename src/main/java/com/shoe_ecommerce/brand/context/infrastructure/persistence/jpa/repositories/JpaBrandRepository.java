package com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.brand.context.infrastructure.persistence.jpa.models.JpaBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaBrandRepository extends JpaRepository<JpaBrand, UUID> {
}
