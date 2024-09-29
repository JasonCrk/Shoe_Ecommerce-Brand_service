package com.shoe_ecommerce.brand.shared.infrastructure;

import com.shoe_ecommerce.brand.shared.domain.UuidGenerator;

import java.util.UUID;

public class JavaUuidGenerator implements UuidGenerator {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
