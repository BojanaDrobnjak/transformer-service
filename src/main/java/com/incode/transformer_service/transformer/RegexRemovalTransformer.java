package com.incode.transformer_service.transformer;

import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.service.Transformer;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.incode.transformer_service.service.TransformerGroup.TEXT_MANIPULATION;

/**
 * Transformer that removes text based on a regular expression.
 */
@Component
public final class RegexRemovalTransformer implements Transformer {

    /**
     * The unique ID of this transformer.
     */
    private static final Long ID = 1L;

    /**
     * The name of the transformer.
     */
    private static final String NAME = "Regex Removal Transformer";

    /**
     * The description of the transformer.
     */
    private static final String
            DESCRIPTION = "Removes text based on a regular expression.";

    /**
     * Applies the regex removal transformation to the provided value.
     *
     * @param value          The value to transform.
     * @param transformerDto The DTO containing transformer details,
     *                       including the regex to remove.
     * @return The transformed value after removing the matched regex.
     */
    @Override
    public String transform(final String value,
                            final TransformerDto transformerDto) {
        String regex = transformerDto.getParameters().get("regex");

        // Check if regex is null or empty
        if (regex == null || regex.isEmpty()) {
            throw new IllegalArgumentException(
                    "Regex parameter is missing or empty.");
        }

        return value.replaceAll(regex, "");
    }

    /**
     * Returns the unique transformer ID.
     *
     * @return The transformer ID.
     */
    @Override
    public Long getTransformerId() {
        return ID;
    }

    /**
     * Returns the group ID of the transformer.
     *
     * @return The group ID.
     */
    @Override
    public Long getGroupId() {
        return TEXT_MANIPULATION.getId();
    }

    /**
     * Returns the name of the transformer.
     *
     * @return The transformer name.
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the description of the transformer.
     *
     * @return The transformer description.
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Returns the parameters of the transformer.
     *
     * @return The transformer parameters.
     */
    @Override
    public List<ParameterDefinition> getParameters() {
        return List.of(
                ParameterDefinition.builder()
                        .name("regex")
                        .description(
                                "The regular expression pattern to replace.")
                        .required(true)
                        .type(ParameterType.STRING)
                        .build()
        );
    }
}
