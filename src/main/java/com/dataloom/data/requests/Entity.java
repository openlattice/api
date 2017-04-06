package com.dataloom.data.requests;

import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.data.EntityKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

public class Entity {
    private EntityKey                 key;
    private SetMultimap<UUID, Object> details;

    @JsonCreator
    public Entity(
            @JsonProperty( SerializationConstants.KEY_FIELD ) EntityKey key,
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) SetMultimap<UUID, Object> details ) {
        this.key = key;
        this.details = details;
    }

    @JsonProperty( SerializationConstants.KEY_FIELD )
    public EntityKey getKey() {
        return key;
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
        result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
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
        if ( key == null ) {
            if ( other.key != null ) return false;
        } else if ( !key.equals( other.key ) ) return false;
        return true;
    }

}
