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
public class EntityType extends TypePK {
    private static final long            serialVersionUID = -9006708363024044315L;
    private final Set<FullQualifiedName> key;
    private final Set<FullQualifiedName> properties;

    @JsonCreator
    public EntityType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.KEY_FIELD ) Set<FullQualifiedName> key,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<FullQualifiedName> properties ) {
        super( id.or( UUID::randomUUID ), type, schemas );
        Preconditions.checkArgument( !key.isEmpty(), "Key properties cannot be empty" );
        this.key = Preconditions.checkNotNull( key, "Entity set key properties cannot be null" );
        this.properties = Preconditions.checkNotNull( properties, "Entity set properties cannot be null" );
    }

    public EntityType(
            FullQualifiedName type,
            Set<FullQualifiedName> schemas,
            Set<FullQualifiedName> key,
            Set<FullQualifiedName> properties ) {
        this( Optional.absent(), type, schemas, key, properties );
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

    // TODO: Don't do this. Make this immutable
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
