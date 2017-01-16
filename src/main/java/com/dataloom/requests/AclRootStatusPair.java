package com.dataloom.requests;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class AclRootStatusPair {
    private List<UUID> aclRoot;
    private EnumSet<RequestStatus>   status;

    public AclRootStatusPair( List<UUID> aclRoot, EnumSet<RequestStatus> status ) {
        this.aclRoot = aclRoot;
        this.status = status;
    }

    public List<UUID> getAclRoot() {
        return aclRoot;
    }

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
