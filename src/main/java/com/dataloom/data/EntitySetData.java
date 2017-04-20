package com.dataloom.data;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;

public class EntitySetData {

    private static final Logger                              logger = LoggerFactory
            .getLogger( EntitySetData.class );

    private Set<FullQualifiedName>                           authorizedPropertyFqns;
    private Iterable<SetMultimap<FullQualifiedName, Object>> entities;

    public EntitySetData(
            Set<FullQualifiedName> authorizedPropertyFqns,
            Iterable<SetMultimap<FullQualifiedName, Object>> entities ) {
        this.authorizedPropertyFqns = authorizedPropertyFqns;
        this.entities = entities;
    }

    /*
     * Warning: this class is not expected to be deserialized except for test purpose.
     */
    @JsonCreator
    public EntitySetData(
            Iterable<SetMultimap<FullQualifiedName, Object>> entities ) {
        this( ImmutableSet.of(), entities );
    }

    public Set<FullQualifiedName> getAuthorizedPropertyFqns() {
        return authorizedPropertyFqns;
    }

    @JsonValue
    public Iterable<SetMultimap<FullQualifiedName, Object>> getEntities() {
        return entities::iterator;
    }

}
