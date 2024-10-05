package com.incode.transformer_service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incode.transformer_service.dto.TransformerRequest;
import com.incode.transformer_service.dto.TransformerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;
import java.util.Map;

import static com.incode.transformer_service.service.TransformerGroup.TEXT_MANIPULATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TransformerControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTransformString() throws Exception {
        // Arrange
        TransformerRequest request = TransformerRequest.builder()
                .value("Hello World")
                .transformers(List.of(
                        TransformerDto.builder()
                                .groupId(TEXT_MANIPULATION.getId())
                                .transformerId(1L)
                                .parameters(Map.of("regex", "[aeiou]"))
                                .build()
                ))
                .build();

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/transformers/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transformedValue").value("Hll Wrld"))
                .andExpect(jsonPath("$.originalValue").value("Hello World"));
    }
}
