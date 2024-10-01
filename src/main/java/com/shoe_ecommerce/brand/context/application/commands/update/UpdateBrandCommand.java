package com.shoe_ecommerce.brand.context.application.commands.update;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.bus.command.Command;

public record UpdateBrandCommand(
        String id,
        String name,
        String about,
        MediaFile logo,
        MediaFile banner
) implements Command {
}
