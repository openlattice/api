package com.dataloom.data;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.SetMultimap;

public class EntitySetData {
    private Set<FullQualifiedName>                           authorizedPropertyFqns;

    @JsonUnwrapped
    private Iterable<SetMultimap<FullQualifiedName, Object>> entities;

    @JsonCreator
    public EntitySetData(
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<FullQualifiedName> authorizedPropertyFqns,
            @JsonProperty( SerializationConstants.ENTITIES ) Iterable<SetMultimap<FullQualifiedName, Object>> entities ) {
        this.authorizedPropertyFqns = authorizedPropertyFqns;
        this.entities = entities;
    }

    @JsonIgnore
    public Set<FullQualifiedName> getAuthorizedPropertyFqns() {
        return authorizedPropertyFqns;
    }

    @JsonProperty( SerializationConstants.ENTITIES )
    public Iterable<SetMultimap<FullQualifiedName, Object>> getEntities() {
        return entities;
    }

}
