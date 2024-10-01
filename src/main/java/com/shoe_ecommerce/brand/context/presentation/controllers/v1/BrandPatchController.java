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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> index(
            @PathVariable("id") UUID id,
            @Valid UpdateBrandRequest request
    ) {
        this.dispatch(new UpdateBrandCommand(
                id.toString(),
                request.name(),
                request.about(),
                request.logo() == null ? null : new MediaFileAdapter(request.banner()),
                request.banner() == null ? null : new MediaFileAdapter(request.banner())
        ));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
