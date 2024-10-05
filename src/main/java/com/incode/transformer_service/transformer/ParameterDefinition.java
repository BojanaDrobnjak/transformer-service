package com.incode.transformer_service.transformer;

import lombok.Builder;
import lombok.Value;

/**
 * Defines a parameter for a transformer.
 */
@Value
@Builder
public class ParameterDefinition {

    /**
     * The name of the parameter (e.g., 'regex').
     */
    private final String name;

    /**
     * The description of the parameter.
     */
    private final String description;

    /**
     * Whether the parameter is required.
     */
    private final boolean required;

    /**
     * The type of the parameter.
     */
    private final ParameterType type;
}
