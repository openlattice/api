package com.dataloom.authorization;

import java.util.Set;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ace {
    private final Principal       principal;
    private final Set<Permission> permissions;
    private transient int         h = 0;

    @JsonCreator
    public Ace(
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.PERMISSIONS ) Set<Permission> permissions ) {
        this.principal = principal;
        this.permissions = permissions;
    }

    @JsonProperty( SerializationConstants.PRINCIPAL )
    public Principal getPrincipal() {
        return principal;
    }

    @JsonProperty( SerializationConstants.PERMISSIONS )
    public Set<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
            result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
            h = result;
        }
        return h;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof Ace ) ) {
            return false;
        }
        Ace other = (Ace) obj;
        if ( permissions == null ) {
            if ( other.permissions != null ) {
                return false;
            }
        } else if ( !permissions.equals( other.permissions ) ) {
            return false;
        }
        if ( principal == null ) {
            if ( other.principal != null ) {
                return false;
            }
        } else if ( !principal.equals( other.principal ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ace [principal=" + principal + ", permissions=" + permissions + "]";
    }

}
