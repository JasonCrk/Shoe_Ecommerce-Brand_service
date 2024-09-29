package com.shoe_ecommerce.brand.shared.domain.bus.query;

public interface QueryHandler<Q extends Query, R extends Response> {
    R handle(Q query);
}
