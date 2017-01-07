package com.dataloom.data.internal;

import java.util.UUID;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

public class Entity {
    private String                    id;
    private SetMultimap<UUID, Object> propertyValues;

    @JsonCreator
    public Entity(
            @JsonProperty( SerializationConstants.ID_FIELD ) String id,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) SetMultimap<UUID, Object> propertyValues ) {
        this.id = id;
        this.propertyValues = propertyValues;
    }

    public String getId() {
        return id;
    }

    public SetMultimap<UUID, Object> getPropertyValues() {
        return propertyValues;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( propertyValues == null ) ? 0 : propertyValues.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Entity other = (Entity) obj;
        if ( id == null ) {
            if ( other.id != null ) return false;
        } else if ( !id.equals( other.id ) ) return false;
        if ( propertyValues == null ) {
            if ( other.propertyValues != null ) return false;
        } else if ( !propertyValues.equals( other.propertyValues ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Entity [id=" + id + ", propertyValues=" + propertyValues + "]";
    }

}
