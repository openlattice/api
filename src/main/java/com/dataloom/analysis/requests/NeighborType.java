package com.dataloom.analysis.requests;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.edm.type.EntityType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NeighborType {

    private final EntityType neighborEntityType;
    private final EntityType associationEntityType;
    private final boolean src;

    @JsonCreator
    public NeighborType(
            @JsonProperty( SerializationConstants.ASSOCIATION_ENTITY_TYPE ) EntityType associationEntityType,
            @JsonProperty( SerializationConstants.NEIGHBOR_ENTITY_TYPE ) EntityType neighborEntityType,
            @JsonProperty( SerializationConstants.SRC ) boolean src
    ) {
        this.neighborEntityType = neighborEntityType;
        this.associationEntityType = associationEntityType;
        this.src = src;
    }

    @JsonProperty( SerializationConstants.NEIGHBOR_ENTITY_TYPE )
    public EntityType getNeighborEntityType() {
        return neighborEntityType;
    }

    @JsonProperty( SerializationConstants.ASSOCIATION_ENTITY_TYPE )
    public EntityType getAssociationEntityType() {
        return associationEntityType;
    }

    @JsonProperty( SerializationConstants.SRC )
    public boolean isSrc() {
        return src;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        NeighborType that = (NeighborType) o;

        if ( src != that.src )
            return false;
        if ( neighborEntityType != null ?
                !neighborEntityType.equals( that.neighborEntityType ) :
                that.neighborEntityType != null )
            return false;
        return associationEntityType != null ?
                associationEntityType.equals( that.associationEntityType ) :
                that.associationEntityType == null;
    }

    @Override public int hashCode() {
        int result = neighborEntityType != null ? neighborEntityType.hashCode() : 0;
        result = 31 * result + ( associationEntityType != null ? associationEntityType.hashCode() : 0 );
        result = 31 * result + ( src ? 1 : 0 );
        return result;
    }

    @Override public String toString() {
        return "NeighborType{" +
                "neighborEntityType=" + neighborEntityType +
                ", associationEntityType=" + associationEntityType +
                ", src=" + src +
                '}';
    }
}
