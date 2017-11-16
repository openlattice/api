package com.dataloom.authorization;

public enum PrincipalType {
    GROUP, //Group principals will come from synchronizing with AD/LDAP and won't be editable in OpenLattice system.
    ORGANIZATION,
    ROLE,
    USER,
    APP
}
