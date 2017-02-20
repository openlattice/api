package com.dataloom.search.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class SearchRequest {

    private final Optional<String>    optionalKeyword;
    private final Optional<UUID>      optionalEntityType;
    private final Optional<Set<UUID>> optionalPropertyTypes;
    private final int                 start;
    private final int                 maxHits;

    @JsonCreator
    public SearchRequest(
            @JsonProperty( SerializationConstants.KEYWORD ) Optional<String> keyword,
            @JsonProperty( SerializationConstants.ENTITY_TYPE_ID ) Optional<UUID> entityType,
            @JsonProperty( SerializationConstants.PROPERTY_TYPE_IDS ) Optional<Set<UUID>> propertyTypes,
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits ) {
        this.optionalKeyword = keyword;
        this.optionalEntityType = entityType;
        this.optionalPropertyTypes = propertyTypes;
        this.start = start;
        this.maxHits = maxHits;
    }

    @JsonProperty( SerializationConstants.KEYWORD )
    public Optional<String> getOptionalKeyword() {
        return optionalKeyword;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE_ID )
    public Optional<UUID> getOptionalEntityType() {
        return optionalEntityType;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPE_IDS )
    public Optional<Set<UUID>> getOptionalPropertyTypes() {
        return optionalPropertyTypes;
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
