package com.shoe_ecommerce.brand.context.presentation.controllers.v1;

import com.shoe_ecommerce.brand.context.application.commands.update.UpdateBrandCommand;
import com.shoe_ecommerce.brand.context.presentation.requests.UpdateBrandRequest;

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

@Tag(name = "PATCHs")
@RestController
@RequestMapping("/api/v1/brands")
public final class BrandPatchController extends RestApiController {

    public BrandPatchController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Edit brand")
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable("id") UUID id,
            @Valid UpdateBrandRequest request,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new UpdateBrandCommand(
                id.toString(),
                associatedBrandId,
                request.name(),
                request.about(),
                Optional.of(request.logo()).map(MediaFileAdapter::new),
                Optional.of(request.banner()).map(MediaFileAdapter::new)
        ));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
