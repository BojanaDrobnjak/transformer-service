package com.incode.transformer_service.transformer;

import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.service.Transformer;
import org.springframework.stereotype.Component;

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
}
