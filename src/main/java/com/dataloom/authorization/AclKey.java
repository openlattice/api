package com.dataloom.authorization;

import java.io.Serializable;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.google.common.base.Preconditions;

/**
 * This class is intended to be used as the key for the hazelcast map storing permission information.
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class AclKey implements Comparable<AclKey>,Serializable {
    private static final long serialVersionUID = 8377824935472079891L;
    private final SecurableObjectType type;
    private final FullQualifiedName fqn;

    public AclKey( SecurableObjectType type, FullQualifiedName id ) {
        this.type = Preconditions.checkNotNull( type );
        this.id = Preconditions.checkNotNull( id );
    }

    public FullQualifiedName getId() {
        return id;
    }

    public SecurableObjectType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
        if ( !( obj instanceof AclKey ) ) {
            return false;
        }
        AclKey other = (AclKey) obj;
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        } else if ( !id.equals( other.id ) ) {
            return false;
        }
        if ( type != other.type ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PermissionKey [objectId=" + id + ", objectType=" + type + "]";
    }

    @Override
    public int compareTo( AclKey o ) {
        int c = type.compareTo( o.getType() );
        return c == 0 ? id.compareTo( o.getId() ) : c;
    }

}
