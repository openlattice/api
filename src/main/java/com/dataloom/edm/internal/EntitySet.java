package com.dataloom.edm.internal;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
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
public class EntitySet extends AbstractSecurableType {
    private static final long serialVersionUID = 1643809693309599032L;
    private final String      name;
    private final String      title;

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
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) String description ) {
        super( id.or( UUID::randomUUID ), type, description, id.isPresent() );
        Preconditions.checkArgument( StringUtils.isNotBlank( name ), "Entity set name cannot be blank." );
        Preconditions.checkArgument( StringUtils.isNotBlank( title ), "Entity set title cannot be blank." );
        this.name = name;
        this.title = title;
    }

    public EntitySet( UUID id, FullQualifiedName type, String name, String title, String description ) {
        this( Optional.of( id ), type, name, title, description );
    }

    public EntitySet( FullQualifiedName type, String name, String title, String description ) {
        this( Optional.absent(), type, name, title, "description" );
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + ( ( title == null ) ? 0 : title.hashCode() );
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
        if ( name == null ) {
            if ( other.name != null ) {
                return false;
            }
        } else if ( !name.equals( other.name ) ) {
            return false;
        }
        if ( title == null ) {
            if ( other.title != null ) {
                return false;
            }
        } else if ( !title.equals( other.title ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntitySet [name=" + name + ", title=" + title + ", type=" + type + ", aclKey=" + aclKey + "]";
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.EntitySet;
    }
}
