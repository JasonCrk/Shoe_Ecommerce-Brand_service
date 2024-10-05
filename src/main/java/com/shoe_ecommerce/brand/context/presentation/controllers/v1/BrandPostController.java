package com.shoe_ecommerce.brand.context.presentation.controllers.v1;

import com.shoe_ecommerce.brand.context.application.commands.create.CreateBrandCommand;
import com.shoe_ecommerce.brand.context.application.commands.follow.FollowBrandCommand;
import com.shoe_ecommerce.brand.context.presentation.requests.CreateBrandRequest;

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

import java.util.Optional;
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
                uuidGenerator.generate(),
                request.name(),
                request.about(),
                Optional.of(request.logo()).map(MediaFileAdapter::new),
                Optional.of(request.banner()).map(MediaFileAdapter::new)
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(operationId = "Follow and unfollow brand")
    @PostMapping("/{id}/follow")
    public ResponseEntity<String> follow(
            @PathVariable("id") UUID brandId,
            @RequestHeader("X-User-Id") String userId
    ) {
        this.dispatch(new FollowBrandCommand(userId, brandId.toString()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
