package com.dataloom.search.requests;

import java.util.List;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvancedSearch {

    private final List<SearchDetails> searches;
    private final int                 start;
    private final int                 maxHits;

    @JsonCreator
    public AdvancedSearch(
            @JsonProperty( SerializationConstants.SEARCH_FIELDS ) List<SearchDetails> searches,
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits ) {
        this.searches = searches;
        this.start = start;
        this.maxHits = maxHits;
    }

    @JsonProperty( SerializationConstants.SEARCH_FIELDS )
    public List<SearchDetails> getSearches() {
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
