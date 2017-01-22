package com.dataloom.requests;

import com.dataloom.authorization.Permission;
import com.dataloom.authorization.Principal;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class Status extends Request {
    private final Principal     principal;
    private final RequestStatus status;

    public Status(
            List<UUID> aclKey,
            Principal principal,
            EnumSet<Permission> permissions,
            RequestStatus status ) {
        super( aclKey, permissions );
        this.status = status;
        this.principal = principal;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
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
