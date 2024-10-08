package com.incode.transformer_service.service;

import lombok.Getter;

/**
 * Enum representing different groups of transformers that can be applied
 * to a string. Each group has an associated ID and a name.
 */
@Getter
public enum TransformerGroup {

    /**
     * Group for transformers that perform text manipulation such as regex-based
     * removal or replacement.
     */
    TEXT_MANIPULATION(1L,
            "Text Manipulation",
            "Transformers that perform text manipulation "
                    + "such as regex-based removal or replacement."),

    /**
     * Group for transformers that perform character conversions such as
     * converting Cyrillic characters to Latin.
     */
    CHARACTER_CONVERSION(2L,
            "Character Conversion",
            "Transformers that perform character conversions "
                    + "such as converting Cyrillic characters to Latin.");

    /**
     * The unique ID associated with the transformer group.
     * -- GETTER --
     * Retrieves the unique ID of the transformer group.
     */
    private final Long id;

    /**
     * The name of the transformer group.
     * -- GETTER --
     * Retrieves the name of the transformer group.
     */
    private final String name;

    /**
     * The description of the transformer group.
     * -- GETTER --
     * Retrieves the description of the transformer group.
     */
    private final String description;

    /**
     * Constructor to create a TransformerGroup enum instance.
     *
     * @param groupId          The unique identifier for the transformer group.
     * @param groupName        The name of the transformer group.
     * @param groupDescription The description of the transformer group.
     */
    TransformerGroup(final Long groupId, final String groupName,
                     final String groupDescription) {
        this.id = groupId;
        this.name = groupName;
        this.description = groupDescription;
    }

}
