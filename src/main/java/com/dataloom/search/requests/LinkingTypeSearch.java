package com.dataloom.search.requests;

import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class LinkingTypeSearch {

    private final Optional<String> optionalSearchTerm;
    private final Optional<UUID>   optionalProperty;
    private final Optional<UUID>   optionalSrc;
    private final Optional<UUID>   optionalDest;
    private final int              start;
    private final int              maxHits;

    @JsonCreator
    public LinkingTypeSearch(
            @JsonProperty( SerializationConstants.KEYWORD ) Optional<String> optionalSearchTerm,
            @JsonProperty( SerializationConstants.PROPERTY_FIELD ) Optional<UUID> optionalProperty,
            @JsonProperty( SerializationConstants.SRC ) Optional<UUID> optionalSrc,
            @JsonProperty( SerializationConstants.DEST ) Optional<UUID> optionalDest,
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits ) {
        this.optionalSearchTerm = optionalSearchTerm;
        this.optionalProperty = optionalProperty;
        this.optionalSrc = optionalSrc;
        this.optionalDest = optionalDest;
        this.start = start;
        this.maxHits = maxHits;
    }

    @JsonProperty( SerializationConstants.KEYWORD )
    public Optional<String> getOptionalSearchTerm() {
        return optionalSearchTerm;
    }

    @JsonProperty( SerializationConstants.PROPERTY_FIELD )
    public Optional<UUID> getOptionalProperty() {
        return optionalProperty;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE_ID )
    public Optional<UUID> getOptionalSrc() {
        return optionalSrc;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPE_IDS )
    public Optional<UUID> getOptionalDest() {
        return optionalDest;
    }

    @JsonProperty( SerializationConstants.START )
    public int getStart() {
        return start;
    }

    @JsonProperty( SerializationConstants.MAX_HITS )
    public int getMaxHits() {
        return maxHits;
    }

}
