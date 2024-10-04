package com.incode.transformer_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

/**
 * Data Transfer Object for transformers.
 */
@Value
@Builder
public class TransformerDto {

    /**
     * Group ID to which the transformer belongs.
     */
    @NotNull(message = "Group ID is required")
    private final Long groupId;

    /**
     * Transformer ID for identifying the transformer.
     */
    @NotNull(message = "Transformer ID is required")
    private final Long transformerId;

    /**
     * Parameters required for the transformer.
     */
    private final Map<String, String> parameters;
}
