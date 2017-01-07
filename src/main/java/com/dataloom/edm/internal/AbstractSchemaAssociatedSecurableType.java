package com.dataloom.edm.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * Internal abstract base class for categorical types in the entity data model.
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public abstract class AbstractSchemaAssociatedSecurableType extends AbstractSecurableType {
    private static final long              serialVersionUID = -154529013746983795L;
    protected final Set<FullQualifiedName> schemas;
    private transient int                  h                = 0;

    // TODO: Consider tracking delta since last write to avoid re-writing entire object on each change.

    protected AbstractSchemaAssociatedSecurableType(
            Optional<UUID> id,
            FullQualifiedName type,
            Set<FullQualifiedName> schemas ) {
        this( id, type, schemas, "" );
    }

    protected AbstractSchemaAssociatedSecurableType(
            Optional<UUID> id,
            FullQualifiedName type,
            Set<FullQualifiedName> schemas,
            String description ) {
        super( id.or( UUID::randomUUID ), type, description, id.isPresent() );
        this.schemas = checkNotNull( schemas );
    }

    @JsonProperty( SerializationConstants.SCHEMAS )
    public Set<FullQualifiedName> getSchemas() {
        return schemas;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ( ( schemas == null ) ? 0 : schemas.hashCode() );
            h = result;
        }
        return h;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof AbstractSchemaAssociatedSecurableType ) ) {
            return false;
        }
        AbstractSchemaAssociatedSecurableType other = (AbstractSchemaAssociatedSecurableType) obj;
        if ( schemas == null ) {
            if ( other.schemas != null ) {
                return false;
            }
        } else if ( !schemas.equals( other.schemas ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypePK [schemas=" + schemas + ", type=" + type + ", aclKey=" + aclKey + "]";
    }

}
