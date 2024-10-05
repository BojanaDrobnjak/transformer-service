package com.incode.transformer_service.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Getter
@Component
public class TransformerFactory {

    /**
     * The list of transformers.
     */
    private final List<Transformer> transformers;

    /**
     * Constructor for TransformerFactory.
     *
     * @param transformersList The list of transformers.
     */
    public TransformerFactory(final List<Transformer> transformersList) {
        this.transformers = transformersList;
    }

    /**
     * Get a transformer by ID.
     *
     * @param transformerId The ID of the transformer to get.
     * @param groupId       The group ID of the transformer to get.
     * @return The transformer with the given ID, if it exists.
     */
    public Optional<Transformer> getTransformerByIdAndGroupId(
            final Long transformerId, final Long groupId) {
        return transformers.stream()
                .filter(transformer ->
                        transformer.getGroupId().equals(groupId)
                                && transformer.getTransformerId().
                                equals(transformerId))
                .findFirst();
    }

}
