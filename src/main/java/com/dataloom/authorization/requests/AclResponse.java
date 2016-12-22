package com.dataloom.authorization.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AclResponse {
    private final Principal                             principal;
    private final Map<List<SecurableObject>, Set<Permission>> aces;

    public AclResponse( Principal principal, Map<List<SecurableObject>, Set<Permission>> aces ) {
        this.principal = principal;
        this.aces = aces;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public Map<List<SecurableObject>, Set<Permission>> getAces() {
        return aces;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aces == null ) ? 0 : aces.hashCode() );
        result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AclResponse other = (AclResponse) obj;
        if ( aces == null ) {
            if ( other.aces != null ) return false;
        } else if ( !aces.equals( other.aces ) ) return false;
        if ( principal == null ) {
            if ( other.principal != null ) return false;
        } else if ( !principal.equals( other.principal ) ) return false;
        return true;
    }

}
