package com.dataloom.requests;

import com.dataloom.authorization.Permission;
import com.dataloom.authorization.Principal;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class Status extends Request {
    private final Principal     principal;
    private final RequestStatus status;

    @JsonCreator
    public Status(
            @JsonProperty( SerializationConstants.ACL_OBJECT_PATH ) List<UUID> aclKey,
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions,
            @JsonProperty( SerializationConstants.STATUS ) RequestStatus status ) {
        super( aclKey, permissions );
        this.status = status;
        this.principal = principal;
    }

    @JsonProperty( SerializationConstants.STATUS )
    public RequestStatus getStatus() {
        return status;
    }

    @JsonProperty( SerializationConstants.PRINCIPAL )
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
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
        if ( !( obj instanceof Status ) ) {
            return false;
        }
        Status other = (Status) obj;
        if ( principal == null ) {
            if ( other.principal != null ) {
                return false;
            }
        } else if ( !principal.equals( other.principal ) ) {
            return false;
        }
        if ( status != other.status ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Status [principal=" + principal + ", status=" + status + ", aclKey=" + aclKey + ", permissions="
                + permissions + "]";
    }

}
