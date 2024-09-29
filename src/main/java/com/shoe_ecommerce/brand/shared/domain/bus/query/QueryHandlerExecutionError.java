package com.shoe_ecommerce.brand.shared.domain.bus.query;

public class QueryHandlerExecutionError extends RuntimeException {
    public QueryHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}

