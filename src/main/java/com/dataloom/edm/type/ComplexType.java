package com.dataloom.edm.type;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.securable.AbstractSchemaAssociatedSecurableType;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class ComplexType extends AbstractSchemaAssociatedSecurableType {
    private final Set<UUID>      properties;
    private final Optional<UUID> baseType;
    private transient int        h = 0;

    @JsonCreator
    public ComplexType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) LinkedHashSet<UUID> properties,
            @JsonProperty( SerializationConstants.PARENT_TYPE_FIELD ) Optional<UUID> baseType ) {
        super( id, type, title, description, schemas );
        this.properties = Preconditions.checkNotNull( properties, "Entity set properties cannot be null" );
        this.baseType = baseType;
    }

    public ComplexType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            LinkedHashSet<UUID> properties,
            Optional<UUID> baseType ) {
        this( Optional.of( id ), type, title, description, schemas, properties, baseType );
    }

    public ComplexType(
            FullQualifiedName type,
            String title,
            String description,
            Set<FullQualifiedName> schemas,
            LinkedHashSet<UUID> properties,
            Optional<UUID> baseType ) {
        this( Optional.absent(), type, title, Optional.of( description ), schemas, properties, baseType );
    }

    @JsonProperty( SerializationConstants.PARENT_TYPE_FIELD )
    public Optional<UUID> getBaseType() {
        return baseType;
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

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ( ( baseType == null ) ? 0 : baseType.hashCode() );
            result = prime * result + ( ( properties == null ) ? 0 : properties.hashCode() );
            h = result;
        }
        return h;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !super.equals( obj ) ) {
            return false;
        }
        if ( !( obj instanceof ComplexType ) ) {
            return false;
        }
        ComplexType other = (ComplexType) obj;
        if ( baseType == null ) {
            if ( other.baseType != null ) {
                return false;
            }
        } else if ( !baseType.equals( other.baseType ) ) {
            return false;
        }
        if ( properties == null ) {
            if ( other.properties != null ) {
                return false;
            }
        } else if ( !properties.equals( other.properties ) ) {
            return false;
        }
        return true;
    }

}
