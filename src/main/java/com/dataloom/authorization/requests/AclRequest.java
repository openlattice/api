package com.dataloom.authorization.requests;

import java.util.EnumSet;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AclRequest {
    @JsonProperty( SerializationConstants.PRINCIPAL )
    protected Principal principal;
    @JsonProperty( SerializationConstants.ACTION )
    protected Action          action;
    @JsonProperty( SerializationConstants.PERMISSIONS )
    protected EnumSet<Permission> permissions;

    public Principal getPrincipal() {
        return principal;
    }

    public Action getAction() {
        return action;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    public AclRequest setPrincipal( Principal principal ) {
        this.principal = principal;
        return this;
    }

    public AclRequest setAction( Action action ) {
        this.action = action;
        return this;
    }

    public AclRequest setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        AclRequest that = (AclRequest) o;

        if ( principal != null ? !principal.equals( that.principal ) : that.principal != null )
            return false;
        if ( action != null ? !action.equals( that.action ) : that.action != null )
            return false;
        return permissions != null ? permissions.equals( that.permissions ) : that.permissions == null;
    }

    @Override
    public int hashCode() {
        int result = principal != null ? principal.hashCode() : 0;
        result = 31 * result + ( action != null ? action.hashCode() : 0 );
        result = 31 * result + ( permissions != null ? permissions.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "AclRequest [principal=" + principal + ", action=" + action + ", permissions=" + permissions + "]";
    }

    @JsonCreator
    public AclRequest createAclRequest(
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions ) {
        return new AclRequest().setPrincipal( principal ).setAction( action ).setPermissions( permissions );
    }

}
