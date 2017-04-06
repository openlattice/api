package com.dataloom.data.requests;

import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

public class Entity {
    private String                    entityId;
    private UUID                      syncId;
    private SetMultimap<UUID, Object> details;

    @JsonCreator
    public Entity(
            @JsonProperty( SerializationConstants.ENTITY_ID ) String entityId,
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId,
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) SetMultimap<UUID, Object> details ) {
        this.entityId = entityId;
        this.syncId = syncId;
        this.details = details;
    }
    
    @JsonProperty( SerializationConstants.ENTITY_ID )
    public String getEntityId() {
        return entityId;
    }
    
    @JsonProperty( SerializationConstants.SYNC_ID )
    public UUID getSyncId() {
        return syncId;
    }

    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    public SetMultimap<UUID, Object> getDetails() {
        return details;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( details == null ) ? 0 : details.hashCode() );
        result = prime * result + ( ( entityId == null ) ? 0 : entityId.hashCode() );
        result = prime * result + ( ( syncId == null ) ? 0 : syncId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Entity other = (Entity) obj;
        if ( details == null ) {
            if ( other.details != null ) return false;
        } else if ( !details.equals( other.details ) ) return false;
        if ( entityId == null ) {
            if ( other.entityId != null ) return false;
        } else if ( !entityId.equals( other.entityId ) ) return false;
        if ( syncId == null ) {
            if ( other.syncId != null ) return false;
        } else if ( !syncId.equals( other.syncId ) ) return false;
        return true;
    }

}
