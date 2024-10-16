package com.shoe_ecommerce.brand.context.presentation.controllers.v1;

import com.shoe_ecommerce.brand.context.application.commands.check.CheckBrandExistsCommand;

import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.brand.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.brand.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Brand - GETs")
@RestController
@RequestMapping("/api/v1/brands")
public final class BrandGetController extends RestApiController {

    public BrandGetController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Check brand exists")
    @GetMapping("/{id}/check")
    public ResponseEntity<String> check(@PathVariable("id") UUID brandId) {
        this.dispatch(new CheckBrandExistsCommand(brandId.toString()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
