package com.shoe_ecommerce.brand.context.application.commands.update;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandAbout;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;
import com.shoe_ecommerce.brand.context.domain.value_objects.BrandName;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandHandler;

@Service
public final class UpdateBrandCommandHandler implements CommandHandler<UpdateBrandCommand, Void> {

    private final BrandEditor editor;

    public UpdateBrandCommandHandler(BrandEditor editor) {
        this.editor = editor;
    }

    @Override
    public Void handle(UpdateBrandCommand command) {
        this.editor.edit(
                new BrandId(command.id()),
                new BrandName(command.name()),
                new BrandAbout(command.about()),
                command.logo(),
                command.banner()
        );

        return null;
    }
}
