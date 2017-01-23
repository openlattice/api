package com.dataloom.requests;

import com.dataloom.authorization.Permission;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class Request {
    protected final List<UUID>          aclKey;
    protected final EnumSet<Permission> permissions;

    public Request( List<UUID> aclKey, EnumSet<Permission> permissions ) {
        this.aclKey = checkNotNull( aclKey, "AclKey cannot be null." );
        checkState( aclKey.size() > 0, "AclKey must have at least one component." );
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
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof Request ) ) {
            return false;
        }
        Request other = (Request) obj;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) {
                return false;
            }
        } else if ( !aclKey.equals( other.aclKey ) ) {
            return false;
        }
        if ( permissions == null ) {
            if ( other.permissions != null ) {
                return false;
            }
        } else if ( !permissions.equals( other.permissions ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request [aclKey=" + aclKey + ", permissions=" + permissions + "]";
    }
    
    

}
