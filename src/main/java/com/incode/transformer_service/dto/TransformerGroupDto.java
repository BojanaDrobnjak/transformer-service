package com.incode.transformer_service.dto;

import lombok.Builder;
import lombok.Value;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Data Transfer Object for transformer groups.
 */
@Value
@Builder
public class TransformerGroupDto {

    /**
     * The ID of the group.
     */
    @NotNull(message = "Group ID is required")
    private final Long groupId;

    /**
     * The name of the group.
     */
    @NotNull(message = "Group name is required")
    private final String groupName;

    /**
     * A description of the group.
     */
    private final String groupDescription;

    /**
     * The list of transformers in this group.
     */
    @NotNull(message = "Transformers list is required")
    private final List<TransformerInfoDto> transformers;
}
