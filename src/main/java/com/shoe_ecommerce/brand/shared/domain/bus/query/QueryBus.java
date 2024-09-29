package com.shoe_ecommerce.brand.shared.domain.bus.query;

public interface QueryBus {
    Response ask(Query query);
}
