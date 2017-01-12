package com.dataloom.requests;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Map;

import com.dataloom.authorization.AclKeyPathFragment;
import com.dataloom.authorization.Permission;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionsRequestDetails implements Serializable {
    private static final long                            serialVersionUID = -3977504707164513197L;

    @JsonProperty( SerializationConstants.PERMISSIONS )
    private Map<AclKeyPathFragment, EnumSet<Permission>> permissions;
    @JsonProperty( SerializationConstants.STATUS )
    private RequestStatus                                status;

    public PermissionsRequestDetails( Map<AclKeyPathFragment, EnumSet<Permission>> permissions, RequestStatus status ) {
        this.permissions = permissions;
        this.status = status;
    }

    public Map<AclKeyPathFragment, EnumSet<Permission>> getPermissions() {
        return permissions;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus( RequestStatus status ) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        PermissionsRequestDetails other = (PermissionsRequestDetails) obj;
        if ( permissions == null ) {
            if ( other.permissions != null ) return false;
        } else if ( !permissions.equals( other.permissions ) ) return false;
        if ( status != other.status ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "PermissionsRequestDetails [permissions=" + permissions + ", status=" + status + "]";
    }
}
