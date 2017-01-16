package com.dataloom.authorization;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessCheck {
    @JsonProperty( SerializationConstants.ACL_OBJECT_PATH )
    private List<UUID> aclKey;
    @JsonProperty( SerializationConstants.PERMISSIONS )
    private EnumSet<Permission>      permissions;

    @JsonCreator
    public AccessCheck(
            @JsonProperty( SerializationConstants.ACL_OBJECT_PATH ) List<UUID> aclKey,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions ) {
        this.aclKey = aclKey;
        this.permissions = permissions;
    }

    public List<UUID> getAclKey() {
        return aclKey;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        result = prime * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AccessCheck other = (AccessCheck) obj;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) return false;
        } else if ( !aclKey.equals( other.aclKey ) ) return false;
        if ( permissions == null ) {
            if ( other.permissions != null ) return false;
        } else if ( !permissions.equals( other.permissions ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "AccessCheck [aclKey=" + aclKey + ", permissions=" + permissions + "]";
    }

}
