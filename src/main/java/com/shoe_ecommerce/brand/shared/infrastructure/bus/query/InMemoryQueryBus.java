package com.shoe_ecommerce.brand.shared.infrastructure.bus.query;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.query.*;

import org.springframework.context.ApplicationContext;

@Service
public class InMemoryQueryBus implements QueryBus {

    private final QueryHandlersInformation information;
    private final ApplicationContext context;

    public InMemoryQueryBus(QueryHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    @Override
    public Response ask(Query query) {
        try {
            Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());
            QueryHandler handler = context.getBean(queryHandlerClass);
            return handler.handle(query);
        } catch (Throwable error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}
