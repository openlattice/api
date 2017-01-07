package com.dataloom.data.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class GetEntitySetRequest {
    @JsonProperty( SerializationConstants.SYNC_IDS )
    private Optional<Set<UUID>> syncIds;
    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    private Optional<Set<UUID>> selectedProperties;

    @JsonCreator
    public GetEntitySetRequest(
            @JsonProperty( SerializationConstants.SYNC_IDS ) Optional<Set<UUID>> syncIds,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Optional<Set<UUID>> selectedProperties ) {
        this.syncIds = syncIds;
        this.selectedProperties = selectedProperties;
    }

    public Optional<Set<UUID>> getSyncIds() {
        return syncIds;
    }

    public Optional<Set<UUID>> getSelectedProperties() {
        return selectedProperties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( selectedProperties == null ) ? 0 : selectedProperties.hashCode() );
        result = prime * result + ( ( syncIds == null ) ? 0 : syncIds.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        GetEntitySetRequest other = (GetEntitySetRequest) obj;
        if ( selectedProperties == null ) {
            if ( other.selectedProperties != null ) return false;
        } else if ( !selectedProperties.equals( other.selectedProperties ) ) return false;
        if ( syncIds == null ) {
            if ( other.syncIds != null ) return false;
        } else if ( !syncIds.equals( other.syncIds ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "GetEntitySetRequest [syncIds=" + syncIds + ", selectedProperties=" + selectedProperties + "]";
    }

}
