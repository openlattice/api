package com.dataloom.requests;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class AclRootRequestDetailsPair implements Serializable {

    private static final long         serialVersionUID = -6090078743805144042L;

    private List<UUID>  aclRoot;
    private PermissionsRequestDetails details;

    public AclRootRequestDetailsPair( List<UUID> aclRoot, PermissionsRequestDetails details ) {
        this.aclRoot = aclRoot;
        this.details = details;
    }

    public List<UUID> getAclRoot() {
        return aclRoot;
    }

    public PermissionsRequestDetails getDetails() {
        return details;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclRoot == null ) ? 0 : aclRoot.hashCode() );
        result = prime * result + ( ( details == null ) ? 0 : details.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AclRootRequestDetailsPair other = (AclRootRequestDetailsPair) obj;
        if ( aclRoot == null ) {
            if ( other.aclRoot != null ) return false;
        } else if ( !aclRoot.equals( other.aclRoot ) ) return false;
        if ( details == null ) {
            if ( other.details != null ) return false;
        } else if ( !details.equals( other.details ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "AclRootRequestDetailsPair [aclRoot=" + aclRoot + ", details=" + details + "]";
    }

}
