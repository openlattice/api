package com.dataloom.edm.internal;

import java.io.Serializable;
import java.util.UUID;

import com.dataloom.authorization.AclKeyPathFragment;
import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public abstract class AbstractSecurableObject implements Serializable {
    private static final long serialVersionUID = -2268620545866476451L;
    protected final AclKeyPathFragment    aclKey;
    protected final String    description;
    private final boolean     idPresent;
    private transient int     h                = 0;

    protected AbstractSecurableObject(
            UUID id,
            boolean idPresent ) {
        this( id, "", idPresent );
    }

    protected AbstractSecurableObject(
            UUID id,
            String description,
            boolean idPresent ) {
        aclKey = new AclKeyPathFragment( this.getCategory(), id );
        this.idPresent = idPresent;
        this.description = description;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return aclKey.getId();
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public AclKeyPathFragment getAclKeyPathFragment() {
        return aclKey;
    }

    @JsonIgnore
    public boolean wasIdPresent() {
        return idPresent;
    }

    @JsonIgnore
    public abstract SecurableObjectType getCategory();

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
            result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
            result = prime * result + ( idPresent ? 1231 : 1237 );
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
        if ( !( obj instanceof AbstractSecurableObject ) ) {
            return false;
        }
        AbstractSecurableObject other = (AbstractSecurableObject) obj;
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
        if ( idPresent != other.idPresent ) {
            return false;
        }
        return true;
    }

}
