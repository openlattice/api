package com.dataloom.data.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class EntitySetSelection {
    private Optional<UUID> syncId;
    private Optional<Set<UUID>> selectedProperties;

    @JsonCreator
    public EntitySetSelection(
            @JsonProperty( SerializationConstants.SYNC_ID ) Optional<UUID> syncId,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Optional<Set<UUID>> selectedProperties ) {
        this.syncId = syncId;
        this.selectedProperties = selectedProperties;
    }
    
    @JsonProperty( SerializationConstants.SYNC_ID )
    public Optional<UUID> getSyncId() {
        return syncId;
    }
    
    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Optional<Set<UUID>> getSelectedProperties() {
        return selectedProperties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( selectedProperties == null ) ? 0 : selectedProperties.hashCode() );
        result = prime * result + ( ( syncId == null ) ? 0 : syncId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        EntitySetSelection other = (EntitySetSelection) obj;
        if ( selectedProperties == null ) {
            if ( other.selectedProperties != null ) return false;
        } else if ( !selectedProperties.equals( other.selectedProperties ) ) return false;
        if ( syncId == null ) {
            if ( other.syncId != null ) return false;
        } else if ( !syncId.equals( other.syncId ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "EntitySetSelection [syncId=" + syncId + ", selectedProperties=" + selectedProperties + "]";
    }

}
