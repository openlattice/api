package com.dataloom.edm.internal;

import java.util.Map;
import java.util.UUID;

import com.dataloom.edm.EdmApi;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EdmDetails {
    @JsonProperty( EdmApi.PROPERTY_TYPES )
    private final Map<UUID, PropertyType> propertyTypes;
    @JsonProperty( EdmApi.ENTITY_TYPES )
    private final Map<UUID, EntityType>   entityTypes;
    @JsonProperty( EdmApi.ENTITY_SETS )
    private final Map<UUID, EntitySet>    entitySets;

    public EdmDetails(
            Map<UUID, PropertyType> propertyTypes,
            Map<UUID, EntityType> entityTypes,
            Map<UUID, EntitySet> entitySets ) {
        this.propertyTypes = propertyTypes;
        this.entityTypes = entityTypes;
        this.entitySets = entitySets;
    }

    public Map<UUID, PropertyType> getPropertyTypes() {
        return propertyTypes;
    }

    public Map<UUID, EntityType> getEntityTypes() {
        return entityTypes;
    }

    public Map<UUID, EntitySet> getEntitySets() {
        return entitySets;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( entitySets == null ) ? 0 : entitySets.hashCode() );
        result = prime * result + ( ( entityTypes == null ) ? 0 : entityTypes.hashCode() );
        result = prime * result + ( ( propertyTypes == null ) ? 0 : propertyTypes.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        EdmDetails other = (EdmDetails) obj;
        if ( entitySets == null ) {
            if ( other.entitySets != null ) return false;
        } else if ( !entitySets.equals( other.entitySets ) ) return false;
        if ( entityTypes == null ) {
            if ( other.entityTypes != null ) return false;
        } else if ( !entityTypes.equals( other.entityTypes ) ) return false;
        if ( propertyTypes == null ) {
            if ( other.propertyTypes != null ) return false;
        } else if ( !propertyTypes.equals( other.propertyTypes ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "EdmDetails [propertyTypes=" + propertyTypes + ", entityTypes=" + entityTypes + ", entitySets="
                + entitySets + "]";
    }

}
