package com.incode.transformer_service.transformer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.service.Transformer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.incode.transformer_service.service.TransformerGroup.CHARACTER_CONVERSION;

/**
 * Transformer that converts Cyrillic characters to Latin equivalents.
 */
@Component
public final class CyrillicToLatinTransformer implements Transformer {

    /**
     * The unique ID of this transformer.
     */
    private static final Long ID = 1L;

    /**
     * The name of the transformer.
     */
    private static final String NAME = "Cyrillic to Latin Transformer";

    /**
     * The description of the transformer.
     */
    private static final String
            DESCRIPTION = "Converts Cyrillic characters to Latin equivalents.";

    /**
     * ResourceLoader to load external resources.
     */
    private final ResourceLoader resourceLoader;

    /**
     * ObjectMapper to parse JSON data.
     */
    private final ObjectMapper objectMapper;

    /**
     * Map for storing Cyrillic to Latin character mappings.
     */
    private final Map<Character, String> cyrillicToLatinMap = new HashMap<>();

    /**
     * Constructor for CyrillicToLatinTransformer.
     *
     * @param loader The ResourceLoader for loading external resources.
     * @param mapper The ObjectMapper for parsing JSON.
     */
    public CyrillicToLatinTransformer(final ResourceLoader loader,
                                      final ObjectMapper mapper) {
        this.resourceLoader = loader;
        this.objectMapper = mapper;
    }

    /**
     * Loads Cyrillic to Latin mappings from a JSON file.
     *
     * @throws IOException if the resource cannot be loaded.
     */
    @PostConstruct
    public void loadCyrillicToLatinMap() throws IOException {
        Resource resource = resourceLoader.getResource(
                "classpath:cyrillic_to_latin.json");

        Map<String, String> jsonMap = objectMapper.readValue(
                resource.getInputStream(), new TypeReference<>() {
                });

        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            cyrillicToLatinMap.put(entry.getKey().charAt(0),
                    entry.getValue());
        }
    }

    /**
     * Applies the Cyrillic to Latin transformation to the provided value.
     *
     * @param value          The value to transform.
     * @param transformerDto The DTO containing transformer details.
     * @return The transformed value.
     */
    @Override
    public String transform(final String value,
                            final TransformerDto transformerDto) {
        StringBuilder result = new StringBuilder();

        for (char ch : value.toCharArray()) {
            result.append(cyrillicToLatinMap.getOrDefault(ch,
                    String.valueOf(ch)));
        }

        return result.toString();
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
        return CHARACTER_CONVERSION.getId();
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
        return List.of();
    }
}
