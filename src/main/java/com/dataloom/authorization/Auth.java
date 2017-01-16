package com.dataloom.authorization;

import java.util.List;
import java.util.Map;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {
    @JsonProperty( SerializationConstants.ACL_OBJECT_PATH )
    private List<AclKeyPathFragment> aclKey;
    @JsonProperty( SerializationConstants.PERMISSIONS_MAP )
    private Map<Permission, Boolean> permissionsMap;

    public Auth( List<AclKeyPathFragment> aclKey, Map<Permission, Boolean> permissionsMap ) {
        this.aclKey = aclKey;
        this.permissionsMap = permissionsMap;
    }

    public List<AclKeyPathFragment> getAclKey() {
        return aclKey;
    }

    public Map<Permission, Boolean> getPermissionsMap() {
        return permissionsMap;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( permissionsMap == null ) ? 0 : permissionsMap.hashCode() );
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Auth other = (Auth) obj;
        if ( permissionsMap == null ) {
            if ( other.permissionsMap != null ) return false;
        } else if ( !permissionsMap.equals( other.permissionsMap ) ) return false;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) return false;
        } else if ( !aclKey.equals( other.aclKey ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Auth [aclKey=" + aclKey + ", accessLevel=" + permissionsMap + "]";
    }

}
