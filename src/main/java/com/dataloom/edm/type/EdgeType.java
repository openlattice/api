package com.dataloom.edm.type;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class EdgeType {

    private Optional<EntityType>          edgeEntityType;
    private LinkedHashSet<UUID> src;
    private LinkedHashSet<UUID> dest;
    private boolean             bidirectional;

    @JsonCreator
    public EdgeType(
            @JsonProperty( SerializationConstants.ENTITY_TYPE ) Optional<EntityType> edgeEntityType,
            @JsonProperty( SerializationConstants.SRC ) LinkedHashSet<UUID> src,
            @JsonProperty( SerializationConstants.DEST ) LinkedHashSet<UUID> dest,
            @JsonProperty( SerializationConstants.BIDIRECTIONAL ) boolean bidirectional ) {
        this.edgeEntityType = edgeEntityType;
        this.src = src;
        this.dest = dest;
        this.bidirectional = bidirectional;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE )
    public EntityType getEdgeEntityType() {
        return edgeEntityType.orNull();
    }

    @JsonProperty( SerializationConstants.SRC )
    public Set<UUID> getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DEST )
    public Set<UUID> getDest() {
        return dest;
    }

    @JsonProperty( SerializationConstants.BIDIRECTIONAL )
    public boolean isBidirectional() {
        return bidirectional;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( bidirectional ? 1231 : 1237 );
        result = prime * result + ( ( dest == null ) ? 0 : dest.hashCode() );
        result = prime * result + ( ( edgeEntityType == null ) ? 0 : edgeEntityType.hashCode() );
        result = prime * result + ( ( src == null ) ? 0 : src.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        EdgeType other = (EdgeType) obj;
        if ( bidirectional != other.bidirectional ) return false;
        if ( dest == null ) {
            if ( other.dest != null ) return false;
        } else if ( !dest.equals( other.dest ) ) return false;
        if ( edgeEntityType == null ) {
            if ( other.edgeEntityType != null ) return false;
        } else if ( !edgeEntityType.equals( other.edgeEntityType ) ) return false;
        if ( src == null ) {
            if ( other.src != null ) return false;
        } else if ( !src.equals( other.src ) ) return false;
        return true;
    }

}
