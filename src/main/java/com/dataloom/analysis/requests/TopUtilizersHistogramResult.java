package com.dataloom.analysis.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TopUtilizersHistogramResult {

    private final List<Map<String, String>> counts;
    private final Set<String>               fields;

    @JsonCreator
    public TopUtilizersHistogramResult(
            @JsonProperty( SerializationConstants.COUNTS ) List<Map<String, String>> counts,
            @JsonProperty( SerializationConstants.FIELDS ) Set<String> fields ) {
        this.counts = counts;
        this.fields = fields;
    }

    @JsonProperty( SerializationConstants.COUNTS )
    public List<Map<String, String>> getCounts() {
        return counts;
    }

    @JsonProperty( SerializationConstants.FIELDS )
    public Set<String> getFields() {
        return fields;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( counts == null ) ? 0 : counts.hashCode() );
        result = prime * result + ( ( fields == null ) ? 0 : fields.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        TopUtilizersHistogramResult other = (TopUtilizersHistogramResult) obj;
        if ( counts == null ) {
            if ( other.counts != null ) return false;
        } else if ( !counts.equals( other.counts ) ) return false;
        if ( fields == null ) {
            if ( other.fields != null ) return false;
        } else if ( !fields.equals( other.fields ) ) return false;
        return true;
    }

}
