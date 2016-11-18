package com.dataloom.edm;

import com.dataloom.edm.internal.EntitySet;
import com.dataloom.edm.internal.EntityType;
import com.dataloom.edm.internal.PropertyType;
import com.dataloom.edm.internal.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityDataModel {
    private final Iterable<String>       namespaces;
    private final Iterable<Schema>       schemas;
    private final Iterable<EntityType>   entityTypes;
    private final Iterable<PropertyType> propertyTypes;
    private final Iterable<EntitySet>    entitySets;

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

}