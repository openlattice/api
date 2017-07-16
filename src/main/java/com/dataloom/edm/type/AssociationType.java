package com.dataloom.edm.type;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.LinkedHashSet;
import java.util.UUID;

public class AssociationType {

    private Optional<EntityType> associationEntityType;
    private LinkedHashSet<UUID>  src;
    private LinkedHashSet<UUID>  dst;
    private boolean              bidirectional;

    @JsonCreator
    public AssociationType(
            @JsonProperty( SerializationConstants.ENTITY_TYPE ) Optional<EntityType> associationEntityType,
            @JsonProperty( SerializationConstants.SRC ) LinkedHashSet<UUID> src,
            @JsonProperty( SerializationConstants.DST ) LinkedHashSet<UUID> dst,
            @JsonProperty( SerializationConstants.BIDIRECTIONAL ) boolean bidirectional ) {
        Preconditions.checkArgument( src.size() > 0, "An association type must have at least one src entity type." );
        Preconditions.checkArgument( dst.size() > 0, "An association type must have at least one dst entity type." );

        this.associationEntityType = associationEntityType;
        this.src = src;
        this.dst = dst;
        this.bidirectional = bidirectional;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE )
    public EntityType getAssociationEntityType() {
        return associationEntityType.orNull();
    }

    @JsonProperty( SerializationConstants.SRC )
    public LinkedHashSet<UUID> getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DST )
    public LinkedHashSet<UUID> getDst() {
        return dst;
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
        result = prime * result + ( ( dst == null ) ? 0 : dst.hashCode() );
        result = prime * result + ( ( associationEntityType == null ) ? 0 : associationEntityType.hashCode() );
        result = prime * result + ( ( src == null ) ? 0 : src.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) { return true; }
        if ( obj == null ) { return false; }
        if ( getClass() != obj.getClass() ) { return false; }
        AssociationType other = (AssociationType) obj;
        if ( bidirectional != other.bidirectional ) { return false; }
        if ( dst == null ) {
            if ( other.dst != null ) { return false; }
        } else if ( !dst.equals( other.dst ) ) { return false; }
        if ( associationEntityType == null ) {
            if ( other.associationEntityType != null ) { return false; }
        } else if ( !associationEntityType.equals( other.associationEntityType ) ) { return false; }
        if ( src == null ) {
            if ( other.src != null ) { return false; }
        } else if ( !src.equals( other.src ) ) { return false; }
        return true;
    }

}
