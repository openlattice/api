package com.dataloom.edm;

import com.dataloom.edm.type.EntityType;
import com.dataloom.edm.type.PropertyType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityDataModel {
    private final Iterable<String>       namespaces;
    private final Iterable<Schema>       schemas;
    private final Iterable<EntityType>   entityTypes;
    private final Iterable<PropertyType> propertyTypes;
    private final Iterable<EntitySet>    entitySets;

    @JsonCreator
    public EntityDataModel(
            @JsonProperty( EdmApi.NAMESPACES ) Iterable<String> namespaces,
            @JsonProperty( EdmApi.SCHEMAS ) Iterable<Schema> schemas,
            @JsonProperty( EdmApi.ENTITY_TYPES ) Iterable<EntityType> entityTypes,
            @JsonProperty( EdmApi.PROPERTY_TYPES ) Iterable<PropertyType> propertyTypes,
            @JsonProperty( EdmApi.ENTITY_SETS ) Iterable<EntitySet> entitySets ) {
        this.namespaces = namespaces;
        this.schemas = schemas;
        this.entityTypes = entityTypes;
        this.propertyTypes = propertyTypes;
        this.entitySets = entitySets;
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

    @JsonProperty( EdmApi.PROPERTY_TYPES )
    public Iterable<PropertyType> getPropertyTypes() {
        return propertyTypes;
    }

    @JsonProperty( EdmApi.ENTITY_SETS )
    public Iterable<EntitySet> getEntitySets() {
        return entitySets;
    }

    //TODO: Null check at creation to avoid doing it on every equals
    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( !( o instanceof EntityDataModel ) )
            return false;

        EntityDataModel that = (EntityDataModel) o;

        if ( namespaces != null ? !namespaces.equals( that.namespaces ) : that.namespaces != null )
            return false;
        if ( schemas != null ? !schemas.equals( that.schemas ) : that.schemas != null )
            return false;
        if ( entityTypes != null ? !entityTypes.equals( that.entityTypes ) : that.entityTypes != null )
            return false;
        if ( propertyTypes != null ? !propertyTypes.equals( that.propertyTypes ) : that.propertyTypes != null )
            return false;
        return entitySets != null ? entitySets.equals( that.entitySets ) : that.entitySets == null;
    }

    @Override
    public int hashCode() {
        int result = namespaces != null ? namespaces.hashCode() : 0;
        result = 31 * result + ( schemas != null ? schemas.hashCode() : 0 );
        result = 31 * result + ( entityTypes != null ? entityTypes.hashCode() : 0 );
        result = 31 * result + ( propertyTypes != null ? propertyTypes.hashCode() : 0 );
        result = 31 * result + ( entitySets != null ? entitySets.hashCode() : 0 );
        return result;
    }

    @Override public String toString() {
        return "EntityDataModel{" +
                "namespaces=" + namespaces +
                ", schemas=" + schemas +
                ", entityTypes=" + entityTypes +
                ", propertyTypes=" + propertyTypes +
                ", entitySets=" + entitySets +
                '}';
    }
}