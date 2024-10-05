package com.incode.transformer_service.transformer;

import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.service.Transformer;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.incode.transformer_service.service.TransformerGroup.TEXT_MANIPULATION;

/**
 * Transformer that replaces text based on a regular expression.
 */
@Component
public final class RegexReplaceTransformer implements Transformer {

    /**
     * The unique ID of this transformer.
     */
    private static final Long ID = 2L;

    /**
     * The name of the transformer.
     */
    private static final String NAME = "Regex Replace Transformer";

    /**
     * The description of the transformer.
     */
    private static final String
            DESCRIPTION = "Replaces text based on a regular expression.";

    /**
     * Applies the regex replacement transformation to the provided value.
     *
     * @param value          The value to transform.
     * @param transformerDto The DTO containing transformer details,
     *                       including regex and replacement.
     * @return The transformed value after applying the regex replacement.
     */
    @Override
    public String transform(final String value,
                            final TransformerDto transformerDto) {
        String regex = transformerDto.getParameters().get("regex");
        String replacement = transformerDto.getParameters().get("replacement");

        // Check for null or empty regex and replacement
        if (regex == null || regex.isEmpty()) {
            throw new IllegalArgumentException(
                    "The 'regex' parameter is required "
                            + "and cannot be null or empty.");
        }
        if (replacement == null) {
            throw new IllegalArgumentException(
                    "The 'replacement' parameter is required "
                            + "and cannot be null.");
        }

        return value.replaceAll(regex, replacement);
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
                        .build(),
                ParameterDefinition.builder()
                        .name("replacement")
                        .description(
                                "The replacement string for matched regex.")
                        .required(true)
                        .type(ParameterType.STRING)
                        .build()
        );
    }
}
