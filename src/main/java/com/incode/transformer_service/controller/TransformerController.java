package com.incode.transformer_service.controller;

import com.incode.transformer_service.dto.TransformerRequest;
import com.incode.transformer_service.dto.TransformerResponse;
import com.incode.transformer_service.service.TransformerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * Controller for handling transformation requests.
 */
@RestController
@RequestMapping("/transform")
public class TransformerController {

    /**
     * Service for applying transformations.
     */
    private final TransformerService transformerService;

    /**
     * Constructor for TransformerController.
     *
     * @param service The TransformerService used to apply transformations.
     */
    public TransformerController(final TransformerService service) {
        this.transformerService = service;
    }

    /**
     * API endpoint to apply transformations to a string
     * based on the provided list of transformers.
     *
     * @param request The request containing the input string
     *                and a list of transformers.
     * @return ResponseEntity containing the original and transformed string.
     */
    @Operation(summary = "Transform a string based on a list of transformers")
    @PostMapping
    public ResponseEntity<TransformerResponse> transform(
            @Valid @RequestBody final TransformerRequest request
    ) {
        String transformedValue = transformerService.applyTransformers(
                request.getValue(), request.getTransformers()
        );
        TransformerResponse response = new TransformerResponse(
                request.getValue(), transformedValue
        );
        return ResponseEntity.ok(response);
    }
}
