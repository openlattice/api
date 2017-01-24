package com.dataloom.authorization;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public enum Role {
    ADMIN( "admin" ),
    USER( "user" ),
    AUTHENTICATED_USER( "AuthenticatedUser" );
    private final Principal principal;

    private Role( String principalId ) {
        this.principal = new Principal( PrincipalType.ROLE, principalId );
    }

    public Principal getPrincipal() {
        return principal;
    }

};