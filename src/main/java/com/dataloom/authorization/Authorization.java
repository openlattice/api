package com.dataloom.authorization;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Authorization {
    private List<UUID>               aclKey;
    private Map<Permission, Boolean> permissions;

    @JsonCreator
    public Authorization( 
            @JsonProperty( SerializationConstants.ACL_OBJECT_PATH ) List<UUID> aclKey, 
            @JsonProperty( SerializationConstants.PERMISSIONS ) Map<Permission, Boolean> permissionsMap ) {
        this.aclKey = aclKey;
        this.permissions = permissionsMap;
    }

    @JsonProperty( SerializationConstants.ACL_OBJECT_PATH )
    public List<UUID> getAclKey() {
        return aclKey;
    }

    @JsonProperty( SerializationConstants.PERMISSIONS )
    public Map<Permission, Boolean> getPermissions() {
        return permissions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Authorization other = (Authorization) obj;
        if ( permissions == null ) {
            if ( other.permissions != null ) return false;
        } else if ( !permissions.equals( other.permissions ) ) return false;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) return false;
        } else if ( !aclKey.equals( other.aclKey ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Authorization [aclKey=" + aclKey + ", permissionsMap=" + permissions + "]";
    }

}
