package com.dataloom.data.requests;

import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.data.EntityKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

public class Event {
    // This is the entityId in the LinkSet table, which can be thought of as an "event id" uniquely identifying this
    // event within the LinkSet
    private String                    entityId;

    private EntityKey                 src;
    private EntityKey                 dst;
    // This is the actual values of the LinkSet table, which can be thought of as "event details" of this event
    private SetMultimap<UUID, Object> details;

    @JsonCreator
    public Event( 
            @JsonProperty( SerializationConstants.ENTITY_ID ) String entityId, 
            @JsonProperty( SerializationConstants.SRC ) EntityKey src, 
            @JsonProperty( SerializationConstants.DEST ) EntityKey dst, 
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) SetMultimap<UUID, Object> details ) {
        this.entityId = entityId;
        this.src = src;
        this.dst = dst;
        this.details = details;
    }

    @JsonProperty( SerializationConstants.ENTITY_ID )
    public String getEntityId() {
        return entityId;
    }

    @JsonProperty( SerializationConstants.SRC )
    public EntityKey getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DEST )
    public EntityKey getDst() {
        return dst;
    }

    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    public SetMultimap<UUID, Object> getDetails() {
        return details;
    }

    @Override
    public int hashCode() {
        // Event Id should uniquely determine the event within an Link Set, same as entity id uniquely determining an
        // entity within entity set
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( entityId == null ) ? 0 : entityId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        // Event Id should uniquely determine the event within an Link Set, same as entity id uniquely determining an
        // entity within entity set
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Event other = (Event) obj;
        if ( entityId == null ) {
            if ( other.entityId != null ) return false;
        } else if ( !entityId.equals( other.entityId ) ) return false;
        return true;
    }

}
