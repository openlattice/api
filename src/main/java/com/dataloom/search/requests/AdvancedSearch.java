package com.dataloom.search.requests;

import java.util.Map;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvancedSearch {
    
    private final Map<UUID, String> searches;
    private final int start;
    private final int maxHits;
    
    public AdvancedSearch(
            @JsonProperty( SerializationConstants.SEARCH_FIELDS ) Map<UUID, String> searches, 
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits ) {
        this.searches = searches;
        this.start = start;
        this.maxHits = maxHits;
    }
    
    @JsonProperty( SerializationConstants.SEARCH_FIELDS ) 
    public Map<UUID, String> getSearches() {
        return searches;
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
