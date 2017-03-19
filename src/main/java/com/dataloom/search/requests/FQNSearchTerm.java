package com.dataloom.search.requests;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FQNSearchTerm {
    
    private final String namespace;
    private final String name;
    private final int start;
    private final int maxHits;
    
    public FQNSearchTerm(
            @JsonProperty( SerializationConstants.NAMESPACE ) String namespace,
            @JsonProperty( SerializationConstants.NAME ) String name,
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits ) {
        this.namespace = namespace;
        this.name = name;
        this.start = start;
        this.maxHits = maxHits;
    }
    
    @JsonProperty( SerializationConstants.NAMESPACE )
    public String getNamespace() {
        return namespace;
    }
    
    @JsonProperty( SerializationConstants.NAME )
    public String getName() {
        return name;
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
