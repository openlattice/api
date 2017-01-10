package com.dataloom.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.UUID;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;

/**
 * This class is intended to be used as the key for the hazelcast map storing permission information.
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class AclKeyPathFragment implements Comparable<AclKeyPathFragment>, Serializable {
    private static final long         serialVersionUID = 8377824935472079891L;
    private final SecurableObjectType type;
    private final UUID                id;
    private transient int             h                = 0;

    @JsonCreator
    public AclKeyPathFragment(
            @JsonProperty( SerializationConstants.TYPE_FIELD ) SecurableObjectType type,
            @JsonProperty( SerializationConstants.ID_FIELD ) UUID id ) {
        this.type = checkNotNull( type );
        this.id = checkNotNull( id );
    }

    @JsonProperty( SerializationConstants.ID_FIELD ) 
    public UUID getId() {
        return id;
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD ) 
    public SecurableObjectType getType() {
        return type;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        /*
         * Assumption here is that locking is more overhead than the cases where hash code is zero (overflow). For
         * problematic instances just map result == 0 to some other fixed constant (slight skew should be fine).
         */
        if ( h == 0 ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
            result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
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
        if ( !( obj instanceof AclKeyPathFragment ) ) {
            return false;
        }
        AclKeyPathFragment other = (AclKeyPathFragment) obj;
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
    public int compareTo( AclKeyPathFragment o ) {
        int c = type.compareTo( o.getType() );
        return c == 0 ? id.compareTo( o.getId() ) : c;
    }

}
