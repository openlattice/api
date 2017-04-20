package com.dataloom.authorization;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
/*
 * Check conductor-client/OrganizationRole to check default Organization Id
 */
public enum SystemRole {
    ADMIN( "admin" ),
    USER( "user" ),
    AUTHENTICATED_USER( "AuthenticatedUser" );
    private final Principal          principal;
    
    private static final Set<String> allRoles;
    private static final String[] allRolesAsArray;

    static {
        allRoles = Stream.of( values() ).map( role -> role.getName() ).collect( Collectors.toSet() );
        allRolesAsArray = allRoles.toArray( new String[ allRoles.size() ] );
    }

    private SystemRole( String principalId ) {
        this.principal = new Principal( PrincipalType.ROLE, principalId );
    }

    public Principal getPrincipal() {
        return principal;
    }

    public String getName() {
        return principal.getId();
    }
    
    public static boolean contains( String role ) {
        return allRoles.contains( role );
    }
    
    public static String[] valuesAsArray() {
        return allRolesAsArray;
    }
};