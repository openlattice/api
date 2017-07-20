package com.dataloom.edm;

import com.dataloom.edm.type.AssociationType;
import com.dataloom.edm.type.EntityType;
import com.dataloom.edm.type.PropertyType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityDataModel {
    private final Iterable<String>          namespaces;
    private final Iterable<Schema>          schemas;
    private final Iterable<EntityType>      entityTypes;
    private final Iterable<AssociationType> associationTypes;
    private final Iterable<PropertyType>    propertyTypes;

    @JsonCreator
    public EntityDataModel(
            @JsonProperty( EdmApi.NAMESPACES ) Iterable<String> namespaces,
            @JsonProperty( EdmApi.SCHEMAS ) Iterable<Schema> schemas,
            @JsonProperty( EdmApi.ENTITY_TYPES ) Iterable<EntityType> entityTypes,
            @JsonProperty( EdmApi.ASSOCIATION_TYPES ) Iterable<AssociationType> associationTypes,
            @JsonProperty( EdmApi.PROPERTY_TYPES ) Iterable<PropertyType> propertyTypes ) {
        this.namespaces = namespaces;
        this.schemas = schemas;
        this.entityTypes = entityTypes;
        this.associationTypes = associationTypes;
        this.propertyTypes = propertyTypes;
    }

    @JsonProperty( EdmApi.NAMESPACES )
    public Iterable<String> getNamespaces() {
        return namespaces;
    }

    @JsonProperty( EdmApi.SCHEMAS )
    public Iterable<Schema> getSchemas() {
        return schemas;
    }

    @JsonProperty( EdmApi.ENTITY_TYPES )
    public Iterable<EntityType> getEntityTypes() {
        return entityTypes;
    }

    @JsonProperty( EdmApi.ASSOCIATION_TYPES )
    public Iterable<AssociationType> getAssociationTypes() {
        return associationTypes;
    }

    @JsonProperty( EdmApi.PROPERTY_TYPES )
    public Iterable<PropertyType> getPropertyTypes() {
        return propertyTypes;
    }

    @Override
    public String toString() {
        return "EntityDataModel [namespaces=" + namespaces + ", schemas=" + schemas + ", entityTypes=" + entityTypes
                + ", associationTypes=" + associationTypes + ", propertyTypes=" + propertyTypes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( associationTypes == null ) ? 0 : associationTypes.hashCode() );
        result = prime * result + ( ( entityTypes == null ) ? 0 : entityTypes.hashCode() );
        result = prime * result + ( ( namespaces == null ) ? 0 : namespaces.hashCode() );
        result = prime * result + ( ( propertyTypes == null ) ? 0 : propertyTypes.hashCode() );
        result = prime * result + ( ( schemas == null ) ? 0 : schemas.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        EntityDataModel other = (EntityDataModel) obj;
        if ( associationTypes == null ) {
            if ( other.associationTypes != null ) return false;
        } else if ( !associationTypes.equals( other.associationTypes ) ) return false;
        if ( entityTypes == null ) {
            if ( other.entityTypes != null ) return false;
        } else if ( !entityTypes.equals( other.entityTypes ) ) return false;
        if ( namespaces == null ) {
            if ( other.namespaces != null ) return false;
        } else if ( !namespaces.equals( other.namespaces ) ) return false;
        if ( propertyTypes == null ) {
            if ( other.propertyTypes != null ) return false;
        } else if ( !propertyTypes.equals( other.propertyTypes ) ) return false;
        if ( schemas == null ) {
            if ( other.schemas != null ) return false;
        } else if ( !schemas.equals( other.schemas ) ) return false;
        return true;
    }

}