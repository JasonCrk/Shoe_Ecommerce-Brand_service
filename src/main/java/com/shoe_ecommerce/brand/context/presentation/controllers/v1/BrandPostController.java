package com.shoe_ecommerce.brand.context.presentation.controllers.v1;

import com.shoe_ecommerce.brand.context.application.commands.create.CreateBrandCommand;
import com.shoe_ecommerce.brand.context.application.commands.follow.FollowBrandCommand;
import com.shoe_ecommerce.brand.context.presentation.requests.CreateBrandRequest;

import com.shoe_ecommerce.brand.context.shared.domain.AuthUser;

import com.shoe_ecommerce.brand.shared.domain.UuidGenerator;
import com.shoe_ecommerce.brand.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.brand.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.brand.shared.infrastructure.MediaFileAdapter;
import com.shoe_ecommerce.brand.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "POSTs")
@RestController
@RequestMapping("/api/v1/brands")
public final class BrandPostController extends RestApiController {

    private final UuidGenerator uuidGenerator;

    public BrandPostController(CommandBus commandBus, QueryBus queryBus, UuidGenerator uuidGenerator) {
        super(commandBus, queryBus);
        this.uuidGenerator = uuidGenerator;
    }

    @Operation(operationId = "Create brand")
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

    @Operation(operationId = "Follow and unfollow brand")
    @PostMapping("/{id}/follow")
    public ResponseEntity<String> follow(
            @PathVariable("id") UUID brandId,
            @RequestAttribute("user") AuthUser user
    ) {
        this.dispatch(new FollowBrandCommand(user.id(), brandId.toString()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
