package com.dataloom.authorization.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Optional;

public class SecurableObjectRequest {
    private final List<SecurableObject>                     secureObject;
    private final Optional<Principal>                       principal;
    private final Optional<Map<Principal, Set<Permission>>> aces;
    private final Optional<Action>                          action;

    @JsonCreator
    public SecurableObjectRequest(
            List<SecurableObject> secureObject,
            Optional<Principal> principal,
            Optional<Map<Principal, Set<Permission>>> aces,
            Optional<Action> action ) {
        this.secureObject = secureObject;
        this.principal = principal;
        this.aces = aces;
        this.action = action;
    }

    public List<SecurableObject> getSecureObject() {
        return secureObject;
    }

    public Optional<Principal> getPrincipal() {
        return principal;
    }

    public Optional<Map<Principal, Set<Permission>>> getAces() {
        return aces;
    }

    public Optional<Action> getAction() {
        return action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aces == null ) ? 0 : aces.hashCode() );
        result = prime * result + ( ( action == null ) ? 0 : action.hashCode() );
        result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
        result = prime * result + ( ( secureObject == null ) ? 0 : secureObject.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        SecurableObjectRequest other = (SecurableObjectRequest) obj;
        if ( aces == null ) {
            if ( other.aces != null ) return false;
        } else if ( !aces.equals( other.aces ) ) return false;
        if ( action == null ) {
            if ( other.action != null ) return false;
        } else if ( !action.equals( other.action ) ) return false;
        if ( principal == null ) {
            if ( other.principal != null ) return false;
        } else if ( !principal.equals( other.principal ) ) return false;
        if ( secureObject == null ) {
            if ( other.secureObject != null ) return false;
        } else if ( !secureObject.equals( other.secureObject ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "SecurableObjectRequest [secureObj=" + secureObject + ", principal=" + principal + ", aces=" + aces
                + ", action=" + action + "]";
    }

}
