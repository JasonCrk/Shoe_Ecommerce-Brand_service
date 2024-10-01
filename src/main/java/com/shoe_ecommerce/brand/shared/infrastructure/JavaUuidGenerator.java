package com.shoe_ecommerce.brand.shared.infrastructure;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public class JavaUuidGenerator implements UuidGenerator {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
