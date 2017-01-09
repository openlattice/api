package com.dataloom.edm.internal;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public final class TestDataFactory {
    private TestDataFactory() {}

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

}
