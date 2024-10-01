package com.shoe_ecommerce.brand.context.application.commands.delete;

import com.shoe_ecommerce.brand.context.domain.value_objects.BrandId;

import com.shoe_ecommerce.brand.shared.domain.Service;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandHandler;

@Service
public final class DeleteBrandCommandHandler implements CommandHandler<DeleteBrandCommand, Void> {

    private final BrandRemover remover;

    public DeleteBrandCommandHandler(BrandRemover remover) {
        this.remover = remover;
    }

    @Override
    public Void handle(DeleteBrandCommand command) {
        this.remover.remove(new BrandId(command.id()));
        return null;
    }
}
