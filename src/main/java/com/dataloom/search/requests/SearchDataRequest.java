package com.dataloom.search.requests;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchDataRequest {
    
    private final String searchTerm;
    private final int start;
    private final int maxHits;
    
    public SearchDataRequest(
            @JsonProperty( SerializationConstants.SEARCH_TERM ) String searchTerm,
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits ) {
        this.searchTerm = searchTerm;
        this.start = start;
        this.maxHits = maxHits;
    }
    
    @JsonProperty( SerializationConstants.SEARCH_TERM )
    public String getSearchTerm() {
        return searchTerm;
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
