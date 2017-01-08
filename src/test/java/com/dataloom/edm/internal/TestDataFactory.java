package com.dataloom.edm.internal;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.google.common.collect.ImmutableSet;

public final class TestDataFactory {
    private TestDataFactory() {}

    public static EntityType entityType() {
        return new EntityType(
                fqn(),
                ImmutableSet.of( fqn(), fqn(), fqn() ),
                ImmutableSet.of( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ),
                ImmutableSet.of( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ) );
    }

    public static FullQualifiedName fqn() {
        return new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) );
    }

}
