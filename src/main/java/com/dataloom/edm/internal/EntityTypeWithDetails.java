package com.dataloom.edm.internal;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.util.Map;

public class EntityTypeWithDetails {

    private final EntityType                           entityType;
    private final Map<FullQualifiedName, PropertyType> properties;

    @JsonCreator
    public EntityTypeWithDetails(
            @JsonProperty( SerializationConstants.ENTITY_FIELD ) EntityType entityType,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Map<FullQualifiedName, PropertyType> properties ) {
        this.entityType = entityType;
        this.properties = properties;
    }

    @JsonProperty( SerializationConstants.ENTITY_FIELD )
    public EntityType getEntityType() {
        return entityType;
    }

    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Map<FullQualifiedName, PropertyType> getProperties() {
        return properties;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        EntityTypeWithDetails that = (EntityTypeWithDetails) o;

        if ( !entityType.equals( that.entityType ) )
            return false;
        return properties.equals( that.properties );

    }

    @Override public int hashCode() {
        int result = entityType.hashCode();
        result = 31 * result + properties.hashCode();
        return result;
    }

    @Override public String toString() {
        return "EntityTypeWithDetails{" +
                "entityType=" + entityType +
                ", properties=" + properties +
                '}';
    }
}
