package com.dataloom.edm.type;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.securable.AbstractSchemaAssociatedSecurableType;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class ComplexType extends AbstractSchemaAssociatedSecurableType {
    private final Set<UUID>   properties;
    
    @JsonCreator
    public ComplexType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<UUID> properties ) {
        super( id, type, title, description, schemas );
        this.properties = new TreeSet<UUID>( Preconditions.checkNotNull( properties, "Entity set properties cannot be null" ) );
    }

    public ComplexType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties ) {
        this( Optional.of( id ), type, title, description, schemas, properties );
    }

    public ComplexType(
            FullQualifiedName type,
            String title,
            String description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties ) {
        this( Optional.absent(), type, title, Optional.of( description ), schemas, properties );
    }
    
    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.COMPLEX_TYPE;
    }

    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Set<UUID> getProperties() {
        return properties;
    }

}
