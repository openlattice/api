package com.dataloom.authorization;

import java.util.Set;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ace {
    private final Principal       principal;
    private final Set<Permission> permissions;

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

}
