package com.incode.transformer_service.unit;

import com.incode.transformer_service.dto.TransformerDto;
import com.incode.transformer_service.exception.ResourceNotFoundException;
import com.incode.transformer_service.service.Transformer;
import com.incode.transformer_service.service.TransformerService;
import com.incode.transformer_service.service.TransformerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.incode.transformer_service.service.TransformerGroup.CHARACTER_CONVERSION;
import static com.incode.transformer_service.service.TransformerGroup.TEXT_MANIPULATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransformerServiceTests {

    private TransformerFactory transformerFactory;
    private TransformerService transformerService;

    @BeforeEach
    void setUp() {
        transformerFactory = mock(TransformerFactory.class);
        transformerService = new TransformerService(transformerFactory);
    }

    @Test
    void testApplyTransformers_withRegexRemoval() {
        // Arrange
        Long transformerId = 2L;
        TransformerDto transformerDto = TransformerDto.builder()
                .groupId(TEXT_MANIPULATION.getId())
                .transformerId(transformerId)
                .parameters(Map.of("regex", "[aeiou]"))
                .build();

        Transformer mockTransformer = mock(Transformer.class);
        when(mockTransformer.transform(anyString(), eq(transformerDto)))
                .thenAnswer(invocation -> {
                    String value = invocation.getArgument(0);
                    return value.replaceAll(transformerDto.getParameters().get("regex"), "");
                });

        // Mock the transformer factory to return the mock transformer
        when(transformerFactory.getTransformerByIdAndGroupId(transformerId, TEXT_MANIPULATION.getId()))
                .thenReturn(Optional.of(mockTransformer));

        // Act
        String result = transformerService.applyTransformers("Hello World", List.of(transformerDto));

        // Assert
        assertEquals("Hll Wrld", result);
    }

    @Test
    void testApplyTransformers_withCyrillicToLatin() {
        // Arrange
        Long transformerId = 1L;
        TransformerDto transformerDto = TransformerDto.builder()
                .groupId(CHARACTER_CONVERSION.getId())
                .transformerId(transformerId)
                .parameters(Map.of())
                .build();

        // Create a mock Transformer for Cyrillic to Latin transformation
        Transformer mockTransformer = mock(Transformer.class);
        when(mockTransformer.transform(anyString(), eq(transformerDto))
        ).thenAnswer(invocation -> {
            String value = invocation.getArgument(0);
            return value
                    .replace("П", "P")
                    .replace("о", "o")
                    .replace("з", "z")
                    .replace("д", "d")
                    .replace("р", "r")
                    .replace("а", "a")
                    .replace("в", "v");
        });

        // Mock the factory to return the mock Transformer
        when(transformerFactory.getTransformerByIdAndGroupId(transformerId, CHARACTER_CONVERSION.getId()))
                .thenReturn(Optional.of(mockTransformer));

        // Act
        String result = transformerService.applyTransformers("Поздрав", List.of(transformerDto));

        // Assert
        assertEquals("Pozdrav", result);
    }

    @Test
    void testApplyTransformers_withInvalidTransformerId() {
        // Arrange
        TransformerDto transformerDto = TransformerDto.builder()
                .groupId(TEXT_MANIPULATION.getId())
                .transformerId(999L) // Invalid transformer ID
                .parameters(Map.of())
                .build();

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            transformerService.applyTransformers("Some Value", List.of(transformerDto));
        });
    }
}