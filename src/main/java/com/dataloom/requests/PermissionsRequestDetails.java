package com.dataloom.requests;

import java.util.EnumSet;
import java.util.Map;
import java.util.UUID;

import com.dataloom.authorization.Permission;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionsRequestDetails {
    private Map<UUID, EnumSet<Permission>> permissions;
    private RequestStatus                  status;

    @JsonCreator
    public PermissionsRequestDetails( 
            @JsonProperty( SerializationConstants.PERMISSIONS ) Map<UUID, EnumSet<Permission>> permissions, 
            @JsonProperty( SerializationConstants.STATUS ) RequestStatus status ) {
        this.permissions = permissions;
        this.status = status;
    }
    
    @JsonProperty( SerializationConstants.PERMISSIONS )
    public Map<UUID, EnumSet<Permission>> getPermissions() {
        return permissions;
    }

    @JsonProperty( SerializationConstants.STATUS )
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
