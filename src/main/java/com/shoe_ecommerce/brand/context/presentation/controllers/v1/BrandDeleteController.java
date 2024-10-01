package com.shoe_ecommerce.brand.context.presentation.controllers.v1;

import com.shoe_ecommerce.brand.context.application.commands.delete.DeleteBrandCommand;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.brand.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.brand.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "DELETEs")
@RestController
@RequestMapping("/api/v1/brands")
public final class BrandDeleteController extends RestApiController {

    private BrandDeleteController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Delete brand")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> index(@PathVariable("id") UUID id) {
        this.dispatch(new DeleteBrandCommand(id.toString()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
