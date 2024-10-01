package com.shoe_ecommerce.brand.context.application.commands.follow;

import com.shoe_ecommerce.brand.shared.domain.bus.command.Command;

public record FollowBrandCommand(String userId, String brandId) implements Command {
}
