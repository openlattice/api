package com.dataloom.requests;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

public class AclRootStatusPair {
    private List<UUID>             aclRoot;
    private EnumSet<RequestStatus> status;

    @JsonCreator
    public AclRootStatusPair(
            @JsonProperty( SerializationConstants.ACL_OBJECT_ROOT ) List<UUID> aclRoot,
            @JsonProperty( SerializationConstants.REQUEST_STATUS ) EnumSet<RequestStatus> status ) {
        this.aclRoot = aclRoot;
        this.status = status;
    }

    @JsonProperty( SerializationConstants.ACL_OBJECT_ROOT )
    public List<UUID> getAclRoot() {
        return aclRoot;
    }

    @JsonProperty( SerializationConstants.REQUEST_STATUS )
    public EnumSet<RequestStatus> getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclRoot == null ) ? 0 : aclRoot.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AclRootStatusPair other = (AclRootStatusPair) obj;
        if ( aclRoot == null ) {
            if ( other.aclRoot != null ) return false;
        } else if ( !aclRoot.equals( other.aclRoot ) ) return false;
        if ( status == null ) {
            if ( other.status != null ) return false;
        } else if ( !status.equals( other.status ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "AclRootStatusPair [aclRoot=" + aclRoot + ", status=" + status + "]";
    }
}
