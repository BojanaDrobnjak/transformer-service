package com.incode.transformer_service.dto;

import com.incode.transformer_service.transformer.ParameterDefinition;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.List;


/**
 * Data Transfer Object for transformers.
 */
@Value
@Builder
public class TransformerInfoDto {

    /**
     * Transformer ID for identifying the transformer.
     */
    @NotNull(message = "Transformer ID is required")
    private final Long transformerId;


    /**
     * The name of the transformer.
     */
    private final String name;

    /**
     * The description of the transformer.
     */
    private final String description;

    /**
     * Parameters for the transformer.
     */
    private final List<ParameterDefinition> parameters;
}
