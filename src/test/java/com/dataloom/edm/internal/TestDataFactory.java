package com.dataloom.edm.internal;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.AclKeyPathFragment;
import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.organization.Organization;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public final class TestDataFactory {
    private static final SecurableObjectType[] securableObjectTypes = SecurableObjectType.values();

    private TestDataFactory() {}

    public static Principal userPrincipal() {
        return new Principal( PrincipalType.USER, RandomStringUtils.randomAlphanumeric( 5 ) );
    }

    public static Principal rolePrincipal() {
        return new Principal( PrincipalType.ROLE, RandomStringUtils.randomAlphanumeric( 5 ) );
    }

    public static EntityType entityType() {
        return new EntityType(
                fqn(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                RandomStringUtils.randomAlphanumeric( 5 ),
                ImmutableSet.of( fqn(), fqn(), fqn() ),
                ImmutableSet.of( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ),
                ImmutableSet.of( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ) );
    }

    public static FullQualifiedName fqn() {
        return new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 5 ),
                RandomStringUtils.randomAlphanumeric( 5 ) );
    }

    public static EntitySet entitySet() {
        return new EntitySet(
                fqn(),
                UUID.randomUUID(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ) );
    }

    public static PropertyType propertyType() {
        return new PropertyType(
                Optional.absent(),
                fqn(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of(),
                EdmPrimitiveTypeKind.String );
    }

    public static Organization organization() {
        return new Organization(
                Optional.absent(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ),
                ImmutableSet.of( RandomStringUtils.randomAlphanumeric( 5 ), RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of( userPrincipal() ),
                ImmutableSet.of( rolePrincipal() ) );
    }

    public static SecurableObjectType securableObjectType() {
        return securableObjectTypes[ RandomUtils.nextInt( 0, securableObjectTypes.length ) ];
    }

    public static AclKeyPathFragment aclKeyPathFragment() {
        return new AclKeyPathFragment( securableObjectType(), UUID.randomUUID() );
    }

}
