package com.dataloom.search.requests;

import java.io.Serializable;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchDetails implements Serializable {
    private static final long serialVersionUID = -6691065251875288358L;

    private final String searchTerm;
    private final UUID propertyType;
    private final boolean exactMatch;
    
    @JsonCreator
    public SearchDetails(
            @JsonProperty( SerializationConstants.SEARCH_TERM ) String searchTerm,
            @JsonProperty( SerializationConstants.PROPERTY_FIELD ) UUID propertyType,
            @JsonProperty( SerializationConstants.EXACT ) boolean exactMatch ) {
        this.searchTerm = searchTerm;
        this.propertyType = propertyType;
        this.exactMatch = exactMatch;
    }
    
    @JsonProperty( SerializationConstants.SEARCH_TERM )
    public String getSearchTerm() {
        return searchTerm;
    }
    
    @JsonProperty( SerializationConstants.PROPERTY_FIELD )
    public UUID getPropertyType() {
        return propertyType;
    }
    
    @JsonProperty( SerializationConstants.EXACT )
    public boolean getExactMatch() {
        return exactMatch;
    }

}
