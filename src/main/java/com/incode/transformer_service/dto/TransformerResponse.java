package com.incode.transformer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object for transformed values.
 */
@Data
@AllArgsConstructor
public class TransformerResponse {

    /**
     * The original value before transformation.
     */
    private String originalValue;

    /**
     * The value after transformation.
     */
    private String transformedValue;
}
