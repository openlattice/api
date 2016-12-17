package com.dataloom.edm.internal;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.AclKey;
import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Internal bstract base class for categorical types in the entity data model.
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public abstract class TypePK implements Serializable {
    private static final long              serialVersionUID = -154529013746983795L;
    protected final FullQualifiedName      type;
    protected final Set<FullQualifiedName> schemas;
    protected final AclKey                 aclKey;

    protected TypePK( UUID id, FullQualifiedName type, Set<FullQualifiedName> schemas ) {
        this.type = type;
        this.schemas = schemas;
        aclKey = new AclKey( this.getCategory(), id );
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return aclKey.getId();
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getType() {
        return type;
    }

    @JsonProperty( SerializationConstants.SCHEMAS )
    public Set<FullQualifiedName> getSchemas() {
        return schemas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        result = prime * result + ( ( schemas == null ) ? 0 : schemas.hashCode() );
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof TypePK ) ) {
            return false;
        }
        TypePK other = (TypePK) obj;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) {
                return false;
            }
        } else if ( !aclKey.equals( other.aclKey ) ) {
            return false;
        }
        if ( schemas == null ) {
            if ( other.schemas != null ) {
                return false;
            }
        } else if ( !schemas.equals( other.schemas ) ) {
            return false;
        }
        if ( type == null ) {
            if ( other.type != null ) {
                return false;
            }
        } else if ( !type.equals( other.type ) ) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    public AclKey getAclKey() {
        return aclKey;
    }

    @JsonIgnore
    public abstract SecurableObjectType getCategory();

    @Override
    public String toString() {
        return "TypePK [type=" + type + ", schemas=" + schemas + ", aclKey=" + aclKey + "]";
    }

}
