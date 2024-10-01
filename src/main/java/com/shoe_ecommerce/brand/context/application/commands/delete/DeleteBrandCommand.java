package com.shoe_ecommerce.brand.context.application.commands.delete;

import com.shoe_ecommerce.brand.shared.domain.bus.command.Command;

public record DeleteBrandCommand(String id) implements Command {
}
