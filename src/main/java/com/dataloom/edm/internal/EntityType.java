package com.dataloom.edm.internal;

import java.util.Set;
import java.util.UUID;

import javax.validation.GroupSequence;
import javax.validation.Valid;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.hibernate.validator.constraints.NotEmpty;

import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.dataloom.edm.validation.ValidateFullQualifiedName;
import com.dataloom.edm.validation.ValidateKeysInProperties;
import com.dataloom.edm.validation.tags.Extended;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

@GroupSequence( { EntityType.class, Extended.class } )
@ValidateKeysInProperties(
    groups = Extended.class )
public class EntityType extends TypePK {
    private static final long      serialVersionUID = -9006708363024044315L;
    @NotEmpty( message = "Key properties of entity type cannot be null or empty." )
    @Valid
    private final Set<@ValidateFullQualifiedName FullQualifiedName> key;
    @NotEmpty( message = "Properties of entity type cannot be null or empty." )
    @Valid
    private final Set<@ValidateFullQualifiedName FullQualifiedName> properties;

    @JsonCreator
    public EntityType(
            @JsonProperty( SerializationConstants.ID_FIELD ) UUID id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.SCHEMAS ) Optional<Set<FullQualifiedName>> schemas,
            @JsonProperty( SerializationConstants.KEY_FIELD ) Set<FullQualifiedName> key,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<FullQualifiedName> properties ) {
        this( id, type, schemas.or( ImmutableSet::of ), key, properties );
    }

    public EntityType(
            UUID id,
            FullQualifiedName type,
            Set<FullQualifiedName> schemas,
            Set<FullQualifiedName> key,
            Set<FullQualifiedName> properties ) {
        super( id, type, schemas );
        this.key = key;
        this.properties = properties;
    }

    // TODO: It seems the objects do not allow property types from the different schemas.
    @JsonProperty( SerializationConstants.KEY_FIELD )
    public Set<FullQualifiedName> getKey() {
        return key;
    }
    
    @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) 
    public Set<FullQualifiedName> getProperties() {
        return properties;
    }

    //TODO: Don't do this. Make this immutable
    public EntityType addProperties( Set<FullQualifiedName> properties ) {
        this.properties.addAll( properties );
        return this;
    }

    public EntityType removeProperties( Set<FullQualifiedName> properties ) {
        this.properties.removeAll( properties );
        return this;
    }

    public SecurableObjectType getCategory() {
        return SecurableObjectType.EntityType;
    }
}
