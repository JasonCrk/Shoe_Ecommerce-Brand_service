package com.shoe_ecommerce.brand.context.application.commands.create;

import com.shoe_ecommerce.brand.shared.domain.MediaFile;
import com.shoe_ecommerce.brand.shared.domain.bus.command.Command;

import java.util.Optional;

public record CreateBrandCommand(
        String id,
        String name,
        String about,
        Optional<MediaFile> logo,
        Optional<MediaFile> banner
) implements Command {
}
