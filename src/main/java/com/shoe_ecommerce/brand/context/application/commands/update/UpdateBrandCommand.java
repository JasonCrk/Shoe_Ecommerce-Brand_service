package com.shoe_ecommerce.brand.context.application.commands.update;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.bus.command.Command;

import java.util.Optional;

public record UpdateBrandCommand(
        String id,
        String associatedBrandId,
        String name,
        String about,
        Optional<MediaFile> logo,
        Optional<MediaFile> banner
) implements Command {
}
