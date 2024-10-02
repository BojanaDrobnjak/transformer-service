package com.incode.transformer_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformerDto {
    private String groupId;
    private String transformerId;
    private Map<String, String> parameters;
}