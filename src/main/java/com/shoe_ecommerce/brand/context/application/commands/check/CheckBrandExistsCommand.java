package com.shoe_ecommerce.brand.context.application.commands.check;

import com.shoe_ecommerce.brand.shared.domain.bus.command.Command;

public record CheckBrandExistsCommand(String brandId) implements Command {
}
