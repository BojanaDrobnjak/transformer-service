package com.incode.transformer_service.service;

import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.dto.TransformerGroupDto;
import com.incode.transformer_service.dto.TransformerInfoDto;
import com.incode.transformer_service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service that applies transformations to a given value using
 * a set of transformers.
 */
@Service
public class TransformerService {

    /**
     * Factory to retrieve transformers by ID.
     */
    private final TransformerFactory transformerFactory;

    /**
     * Constructor for TransformerService.
     *
     * @param factory The factory used to get transformers by ID.
     */
    public TransformerService(final TransformerFactory factory) {
        this.transformerFactory = factory;
    }

    /**
     * Apply a list of transformers to a value.
     *
     * @param value        The value to transform.
     * @param transformers The transformers to apply.
     * @return The transformed value.
     */
    public String applyTransformers(
            final String value, final List<TransformerDto> transformers
    ) {
        List<TransformerDto> mutableTransformers =
                new ArrayList<>(transformers);
        // Sort the transformers by group ID and transformer ID.
        mutableTransformers.sort(Comparator
                .comparing(TransformerDto::getGroupId)
                .thenComparing(TransformerDto::getTransformerId));

        String transformedValue = value;
        for (TransformerDto transformerDto : mutableTransformers) {
            transformedValue =
                    applyTransformer(transformedValue, transformerDto);
        }
        return transformedValue;
    }

    /**
     * Apply a single transformer to a value.
     *
     * @param value          The value to transform.
     * @param transformerDto The transformer to apply.
     * @return The transformed value.
     */
    private String applyTransformer(
            final String value, final TransformerDto transformerDto
    ) {
        Long transformerId = transformerDto.getTransformerId();
        Long groupId = transformerDto.getGroupId();
        return transformerFactory
                .getTransformerByIdAndGroupId(transformerId, groupId)
                .map(transformer -> transformer.transform(
                        value, transformerDto))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Transformer with id " + transformerId
                                + " and group " + groupId + " not found")
                );
    }

    /**
     * Get all transformer groups and their transformers.
     *
     * @return A list of transformer groups with their transformers.
     */
    public List<TransformerGroupDto> getAllTransformerGroups() {
        // Get all transformers from the factory
        List<Transformer> transformers = transformerFactory.getTransformers();

        // Group transformers by their groupId (TransformerGroup)
        Map<Long, List<Transformer>> groupedTransformers = transformers.stream()
                .collect(Collectors.groupingBy(Transformer::getGroupId));

        // Return all groups with transformers populated if they exist
        return Stream.of(TransformerGroup.values())
                .map(group -> TransformerGroupDto.builder()
                        .groupId(group.getId())
                        .groupName(group.name())
                        .groupDescription(group.getDescription())
                        .transformers(groupedTransformers
                                .getOrDefault(group.getId(), List.of()).stream()
                                .map(transformer -> TransformerInfoDto.builder()
                                        .transformerId(transformer
                                                .getTransformerId())
                                        .name(transformer.getName())
                                        .description(transformer
                                                .getDescription())
                                        .parameters(transformer.getParameters())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
