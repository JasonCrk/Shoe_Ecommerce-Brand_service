package com.shoe_ecommerce.brand.context.presentation.controllers.v1;

import com.shoe_ecommerce.brand.context.application.commands.create.CreateBrandCommand;
import com.shoe_ecommerce.brand.context.presentation.requests.CreateBrandRequest;

import com.shoe_ecommerce.brand.shared.domain.UuidGenerator;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.brand.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.brand.shared.infrastructure.MediaFileAdapter;
import com.shoe_ecommerce.brand.shared.infrastructure.RestApiController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
public final class BrandPostController extends RestApiController {

    private final UuidGenerator uuidGenerator;

    public BrandPostController(CommandBus commandBus, QueryBus queryBus, UuidGenerator uuidGenerator) {
        super(commandBus, queryBus);
        this.uuidGenerator = uuidGenerator;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid CreateBrandRequest request) {
        this.<Void>dispatch(new CreateBrandCommand(
                uuidGenerator.generate().toString(),
                request.name(),
                request.about(),
                new MediaFileAdapter(request.logo()),
                new MediaFileAdapter(request.banner())
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}