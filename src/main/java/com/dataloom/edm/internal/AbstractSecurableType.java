package com.dataloom.edm.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.AclKey;
import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractSecurableType implements Serializable {
    private static final long         serialVersionUID = 7806377281682752296L;
    protected final FullQualifiedName type;
    protected final AclKey            aclKey;
    protected final String            description;
    private final boolean             idPresent;
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
        this.type = checkNotNull( type );
        aclKey = new AclKey( this.getCategory(), id );
        this.idPresent = idPresent;
        this.description = description;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return aclKey.getId();
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public AclKey getAclKey() {
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
        if ( idPresent != other.idPresent ) {
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
