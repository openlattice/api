package com.dataloom.requests;

import com.openlattice.authorization.AclKey;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import com.dataloom.authorization.Permission;
import com.dataloom.authorization.Principal;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class Status {
    private final Request       request;
    private final Principal     principal;
    private final RequestStatus status;

    @JsonCreator
    public Status(
            @JsonProperty( SerializationConstants.REQUEST ) Request request,
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.STATUS ) RequestStatus status ) {
        this.request = request;
        this.principal = principal;
        this.status = status;
    }

    public Status(
            AclKey aclKey,
            EnumSet<Permission> permissions,
            String reason,
            Principal principal,
            RequestStatus status ) {
        this( new Request( aclKey, permissions, Optional.fromNullable( reason ) ), principal, status );
    }

    @JsonProperty( SerializationConstants.REQUEST )
    public Request getRequest() {
        return request;
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
        int result = 1;
        result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
        result = prime * result + ( ( request == null ) ? 0 : request.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Status other = (Status) obj;
        if ( principal == null ) {
            if ( other.principal != null ) return false;
        } else if ( !principal.equals( other.principal ) ) return false;
        if ( request == null ) {
            if ( other.request != null ) return false;
        } else if ( !request.equals( other.request ) ) return false;
        if ( status != other.status ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Status [request=" + request + ", principal=" + principal + ", status=" + status + "]";
    }

}
