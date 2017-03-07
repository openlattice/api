package com.dataloom.edm.type;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.securable.AbstractSchemaAssociatedSecurableType;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class ComplexType extends AbstractSchemaAssociatedSecurableType {
    private final Set<UUID> properties;
    private final UUID      parentType;

    @JsonCreator
    public ComplexType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) TreeSet<UUID> properties,
            @JsonProperty( SerializationConstants.PARENT_TYPE_FIELD ) UUID parentType ) {
        super( id, type, title, description, schemas );
        this.properties = Preconditions.checkNotNull( properties, "Entity set properties cannot be null" );
        this.parentType = parentType;
    }

    public ComplexType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties
            UUID parentType) {
        this( Optional.of( id ), type, title, description, schemas, properties , parentType );
    }

    public ComplexType(
            FullQualifiedName type,
            String title,
            String description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties, ) {
        this( Optional.absent(), type, title, Optional.of( description ), schemas, properties, parentType );
    }

    @JsonProperty( SerializationConstants.PARENT_TYPE_FIELD )
    public UUID getParentType() {
        return parentType;
    }

    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Set<UUID> getProperties() {
        return properties;
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.COMPLEX_TYPE;
    }

}
