package com.shoe_ecommerce.brand.shared.domain.bus.command;

public class CommandNotRegisteredError extends Exception {
    public CommandNotRegisteredError(Class<? extends Command> command) {
        super(String.format("The command <%s> hasn't a command handler associated", command.getName()));
    }
}
