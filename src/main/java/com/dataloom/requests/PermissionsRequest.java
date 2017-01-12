package com.dataloom.requests;

import java.util.List;
import java.util.UUID;

import com.dataloom.authorization.AclKeyPathFragment;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionsRequest {
    @JsonProperty( SerializationConstants.ACL_OBJECT_ROOT )
    private List<AclKeyPathFragment>  aclRoot;
    @JsonProperty( SerializationConstants.REQUESTING_USER )
    private String                    userId;
    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    private PermissionsRequestDetails details;

    public PermissionsRequest(
            List<AclKeyPathFragment> aclRoot,
            String userId,
            PermissionsRequestDetails details ) {
        this.aclRoot = aclRoot;
        this.userId = userId;
        this.details = details;
    }

    public List<AclKeyPathFragment> getAclRoot() {
        return aclRoot;
    }

    public String getUserId() {
        return userId;
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
        result = prime * result + ( ( userId == null ) ? 0 : userId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        PermissionsRequest other = (PermissionsRequest) obj;
        if ( aclRoot == null ) {
            if ( other.aclRoot != null ) return false;
        } else if ( !aclRoot.equals( other.aclRoot ) ) return false;
        if ( details == null ) {
            if ( other.details != null ) return false;
        } else if ( !details.equals( other.details ) ) return false;
        if ( userId == null ) {
            if ( other.userId != null ) return false;
        } else if ( !userId.equals( other.userId ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "PermissionsRequest [aclRoot=" + aclRoot + ", userId=" + userId + ", details=" + details + "]";
    }

}
