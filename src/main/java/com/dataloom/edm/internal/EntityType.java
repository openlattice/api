package com.dataloom.edm.internal;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class EntityType extends AbstractSchemaAssociatedSecurableType {
    private static final long serialVersionUID = -9006708363024044315L;
    private final Set<UUID>   key;
    private final Set<UUID>   properties;
    private transient int     h                = 0;

    @JsonCreator
    public EntityType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.KEY_FIELD ) Set<UUID> key,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<UUID> properties ) {
        super( id, type, schemas );
        Preconditions.checkArgument( !key.isEmpty(), "Key properties cannot be empty" );
        this.key = Preconditions.checkNotNull( key, "Entity set key properties cannot be null" );
        this.properties = Preconditions.checkNotNull( properties, "Entity set properties cannot be null" );
    }

    public EntityType(
            UUID id,
            FullQualifiedName type,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties ) {
        this( Optional.of( id ), type, schemas, key, properties );
    }

    public EntityType(
            FullQualifiedName type,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties ) {
        this( Optional.absent(), type, schemas, key, properties );
    }

    // TODO: It seems the objects do not allow property types from the different schemas.
    @JsonProperty( SerializationConstants.KEY_FIELD )
    public Set<UUID> getKey() {
        return key;
    }

    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Set<UUID> getProperties() {
        return properties;
    }

    public SecurableObjectType getCategory() {
        return SecurableObjectType.EntityType;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
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
        if ( !( obj instanceof EntityType ) ) {
            return false;
        }
        EntityType other = (EntityType) obj;
        if ( key == null ) {
            if ( other.key != null ) {
                return false;
            }
        } else if ( !key.equals( other.key ) ) {
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
