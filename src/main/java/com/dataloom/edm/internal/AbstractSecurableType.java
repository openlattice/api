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
    private final boolean             idPresent;
    private transient int             h                = 0;

    protected AbstractSecurableType(
            UUID id,
            FullQualifiedName type,
            boolean idPresent ) {
        this.type = checkNotNull( type );
        aclKey = new AclKey( this.getCategory(), id );
        this.idPresent = idPresent;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return aclKey.getId();
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getType() {
        return type;
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
            result = prime * result + ( idPresent ? 1231 : 1237 );
            result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
            h = result;
        }
        return h;
    }

}
