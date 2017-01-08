package com.dataloom.edm.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt; 
 *
 */
public abstract class AbstractSecurableType extends AbstractSecurableObject {
    private static final long         serialVersionUID = 7806377281682752296L;
    protected final FullQualifiedName type;
    private transient int             h                = 0;

    protected AbstractSecurableType(
            UUID id,
            FullQualifiedName type,
            boolean idPresent ) {
        this( id, type, "", idPresent );
    }

    protected AbstractSecurableType(
            UUID id,
            FullQualifiedName type,
            String description,
            boolean idPresent ) {
        super( id, description, idPresent );
        this.type = checkNotNull( type );
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getType() {
        return type;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
            result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
            result = h;
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
        if ( !( obj instanceof AbstractSecurableType ) ) {
            return false;
        }
        AbstractSecurableType other = (AbstractSecurableType) obj;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) {
                return false;
            }
        } else if ( !aclKey.equals( other.aclKey ) ) {
            return false;
        }
        if ( description == null ) {
            if ( other.description != null ) {
                return false;
            }
        } else if ( !description.equals( other.description ) ) {
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

}
