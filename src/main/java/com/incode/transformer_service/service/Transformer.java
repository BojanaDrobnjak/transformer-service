package com.incode.transformer_service.service;

import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.transformer.ParameterDefinition;

import java.util.List;

/**
 * Interface representing a transformer that applies transformations
 * to a given value.
 */
public interface Transformer {

    /**
     * Applies a transformation to the provided value.
     *
     * @param value The value to transform.
     * @param transformerDto The DTO containing transformer details.
     * @return The transformed value.
     */
    String transform(String value, TransformerDto transformerDto);

    /**
     * Returns the unique ID of the transformer.
     *
     * @return The transformer ID.
     */
    Long getTransformerId();

    /**
     * Returns the group ID of the transformer.
     *
     * @return The group ID.
     */
    Long getGroupId();

    /**
     * Returns the name of the transformer.
     *
     * @return The transformer name.
     */
    String getName();

    /**
     * Returns the description of the transformer.
     *
     * @return The transformer description.
     */
    String getDescription();

    /**
     * Returns the parameters of the transformer.
     *
     * @return The transformer parameters.
     */
    List<ParameterDefinition> getParameters();
}
