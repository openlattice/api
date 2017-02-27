package com.dataloom.requests;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import com.dataloom.authorization.Permission;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class Request {
    protected final List<UUID>          aclKey;
    protected final EnumSet<Permission> permissions;
    protected final String              reason;

    @JsonCreator
    public Request(
            @JsonProperty( SerializationConstants.ACL_OBJECT_PATH ) List<UUID> aclKey,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions,
            @JsonProperty( SerializationConstants.REASON ) Optional<String> reason ) {
        this( aclKey, permissions, reason.or( "" ) );
    }

    public Request( List<UUID> aclKey, EnumSet<Permission> permissions, String reason ) {
        this.aclKey = checkNotNull( aclKey, "AclKey cannot be null." );
        checkState( aclKey.size() > 0, "AclKey must have at least one component." );
        this.permissions = permissions;
        this.reason = reason;
    }

    @JsonProperty( SerializationConstants.ACL_OBJECT_PATH )
    public List<UUID> getAclKey() {
        return aclKey;
    }

    @JsonProperty( SerializationConstants.PERMISSIONS )
    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    @JsonProperty( SerializationConstants.REASON )
    public String getReason() {
        return reason;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        result = prime * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
        result = prime * result + ( ( reason == null ) ? 0 : reason.hashCode() );
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
        if ( reason == null ) {
            if ( other.reason != null ) {
                return false;
            }
        } else if ( !reason.equals( other.reason ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request [aclKey=" + aclKey + ", permissions=" + permissions + ", reasons=" + reason + "]";
    }

}
