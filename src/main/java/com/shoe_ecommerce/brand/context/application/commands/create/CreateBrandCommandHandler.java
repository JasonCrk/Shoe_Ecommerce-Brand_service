package com.shoe_ecommerce.brand.context.application.commands.create;

import com.shoe_ecommerce.brand.context.domain.value_objects.*;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateBrandCommandHandler implements CommandHandler<CreateBrandCommand, Void> {

    private final BrandCreator creator;

    public CreateBrandCommandHandler(BrandCreator creator) {
        this.creator = creator;
    }

    @Override
    public Void handle(CreateBrandCommand command) {
        this.creator.create(
                new BrandId(command.id()),
                new BrandName(command.name()),
                new BrandAbout(command.about()),
                command.logo(),
                command.banner()
        );

        return null;
    }
}
