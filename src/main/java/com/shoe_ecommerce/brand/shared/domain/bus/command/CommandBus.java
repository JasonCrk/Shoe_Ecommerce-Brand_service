package com.shoe_ecommerce.brand.shared.domain.bus.command;

public interface CommandBus {
    <R> R dispatch(Command command) throws CommandHandlerExecutionError;
}
