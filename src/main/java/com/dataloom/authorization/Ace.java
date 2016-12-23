package com.dataloom.authorization;

import java.util.Set;

import com.dataloom.authorization.requests.Permission;
import com.dataloom.authorization.requests.Principal;

public class Ace {
    private final Principal       principal;
    private final Set<Permission> permissions;
    
    public Ace( Principal principal, Set<Permission> permissions ) {
        this.principal = principal;
        this.permissions = permissions;
    }
    public Principal getPrincipal() {
        return principal;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

}
