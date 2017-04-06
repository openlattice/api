package com.dataloom.data.requests;

import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.data.EntityKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

public class Connection {
    // This is the entityId in the LinkSet, which can be thought of as an "connection id" uniquely identifying this
    // connection within the LinkSet
    private String                    entityId;
    private UUID                      syncId;

    private EntityKey                 src;
    private EntityKey                 dst;
    // This is the actual values of the LinkSet, which can be thought of as "connection details" of this connection
    private SetMultimap<UUID, Object> details;

    @JsonCreator
    public Connection(
            @JsonProperty( SerializationConstants.ENTITY_ID ) String entityId,
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId,
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

    @JsonProperty( SerializationConstants.SYNC_ID )
    public UUID getSyncId() {
        return syncId;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( details == null ) ? 0 : details.hashCode() );
        result = prime * result + ( ( dst == null ) ? 0 : dst.hashCode() );
        result = prime * result + ( ( entityId == null ) ? 0 : entityId.hashCode() );
        result = prime * result + ( ( src == null ) ? 0 : src.hashCode() );
        result = prime * result + ( ( syncId == null ) ? 0 : syncId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Connection other = (Connection) obj;
        if ( details == null ) {
            if ( other.details != null ) return false;
        } else if ( !details.equals( other.details ) ) return false;
        if ( dst == null ) {
            if ( other.dst != null ) return false;
        } else if ( !dst.equals( other.dst ) ) return false;
        if ( entityId == null ) {
            if ( other.entityId != null ) return false;
        } else if ( !entityId.equals( other.entityId ) ) return false;
        if ( src == null ) {
            if ( other.src != null ) return false;
        } else if ( !src.equals( other.src ) ) return false;
        if ( syncId == null ) {
            if ( other.syncId != null ) return false;
        } else if ( !syncId.equals( other.syncId ) ) return false;
        return true;
    }

}
