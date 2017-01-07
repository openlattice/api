package com.dataloom.data.requests;

import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import com.dataloom.data.internal.Entity;
import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.serializers.jackson.AbstractBaseJacksonSerializationTest;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

/**
 * Test for create entity request serialization
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class CreateEntityRequestSerializerTest extends AbstractBaseJacksonSerializationTest<CreateEntityRequest> {
    private static final SetMultimap<UUID, Object> a        = HashMultimap.create();
    private static final SetMultimap<UUID, Object> b        = HashMultimap.create();
    private static final Set<Entity>               entities = Sets.newLinkedHashSetWithExpectedSize( 2 );

    static {
        registerModule( FullQualifedNameJacksonSerializer::registerWithMapper );
        registerModule( FullQualifedNameJacksonDeserializer::registerWithMapper );
        a.put( UUID.randomUUID(), "A" );
        a.put( UUID.randomUUID(), "ABC" );
        b.put( UUID.randomUUID(), "100" );
        b.put( UUID.randomUUID(), "101" );
        b.put( UUID.randomUUID(), 102 );
        Entity e1 = new Entity( RandomStringUtils.randomAlphanumeric( 10 ), a );
        Entity e2 = new Entity( RandomStringUtils.randomAlphanumeric( 10 ), b );
        entities.add( e1 );
        entities.add( e2 );
    }

    @Override
    protected CreateEntityRequest getSampleData() {
        return new CreateEntityRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                entities );
    }

    @Override
    protected Class<CreateEntityRequest> getClazz() {
        return CreateEntityRequest.class;
    }

}
