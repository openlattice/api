package com.dataloom.edm.internal;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class EntitySet extends AbstractSecurableType {
    private final UUID        entityTypeId;
    private final String      name;

    /**
     * Creates an entity set with provided parameters and will automatically generate a UUID if not provided.
     * 
     * @param id An optional UUID for the entity set.
     * @param type The full qualified name of the entity type to be collected in this entity set.
     * @param name The name of the entity set.
     * @param title The friendly name for the entity set.
     * @param description A description of the entity set.
     */
    @JsonCreator
    public EntitySet(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.ENTITY_TYPE_ID_FIELD ) UUID entityTypeId,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {
        super( id, type, title, description );
        checkArgument( StringUtils.isNotBlank( name ), "Entity set name cannot be blank." );
        checkArgument( StringUtils.isNotBlank( title ), "Entity set title cannot be blank." );
        this.name = name;
        this.entityTypeId = checkNotNull( entityTypeId );
    }

    public EntitySet(
            UUID id,
            FullQualifiedName type,
            UUID entityTypeId,
            String name,
            String title,
            Optional<String> description ) {
        this( Optional.of( id ), type, entityTypeId, name, title, description );
    }

    public EntitySet(
            FullQualifiedName type,
            UUID entityTypeId,
            String name,
            String title,
            Optional<String> description ) {
        this( Optional.absent(), type, entityTypeId, name, title, description );
    }

    public UUID getEntityTypeId() {
        return entityTypeId;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( entityTypeId == null ) ? 0 : entityTypeId.hashCode() );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !super.equals( obj ) ) {
            return false;
        }
        if ( !( obj instanceof EntitySet ) ) {
            return false;
        }
        EntitySet other = (EntitySet) obj;
        if ( entityTypeId == null ) {
            if ( other.entityTypeId != null ) {
                return false;
            }
        } else if ( !entityTypeId.equals( other.entityTypeId ) ) {
            return false;
        }
        if ( name == null ) {
            if ( other.name != null ) {
                return false;
            }
        } else if ( !name.equals( other.name ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntitySet [entityTypeId=" + entityTypeId + ", name=" + name + ", type=" + type + ", id=" + id
                + ", title=" + title + ", description=" + description + "]";
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.EntitySet;
    }
}
