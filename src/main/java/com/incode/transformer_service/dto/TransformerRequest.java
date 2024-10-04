package com.incode.transformer_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;

/**
 * Request object for applying transformations to a value.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformerRequest {

    /**
     * The value to be transformed.
     */
    @NotEmpty(message = "Value is required")
    private String value;

    /**
     * A list of transformers to apply to the value.
     */
    @NotEmpty(message = "At least one transformer is required")
    private List<TransformerDto> transformers;
}
