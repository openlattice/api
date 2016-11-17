package com.dataloom.authorization.requests;

import java.util.EnumSet;

public class PermissionsInfo {
    private Principal principal;
    private EnumSet<Permission> permissions;
    
    public Principal getPrincipal() {
        return principal;
    }
    
    public PermissionsInfo setPrincipal( Principal principal ) {
        this.principal = principal;
        return this;
    }
    
    public EnumSet<Permission> getPermissions() {
        return permissions;
    }
    
    public PermissionsInfo setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }
    
    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        PermissionsInfo that = (PermissionsInfo) o;

        if ( principal != null ? !principal.equals( that.principal ) : that.principal != null )
            return false;
        return permissions != null ? permissions.equals( that.permissions ) : that.permissions == null;
    }

    @Override
    public int hashCode() {
        int result = principal != null ? principal.hashCode() : 0;
        result = 31 * result + ( permissions != null ? permissions.hashCode() : 0 );
        return result;
    }
}
