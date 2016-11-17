package com.dataloom.data.requests;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.kryptnostic.rhizome.hazelcast.serializers.BaseJacksonSerializationTest;

/**
 * Test for create entity request serialization
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class CreateEntityRequestSerializerTest extends BaseJacksonSerializationTest<CreateEntityRequest> {
    private static final SetMultimap<FullQualifiedName, Object>      a              = HashMultimap.create();
    private static final SetMultimap<FullQualifiedName, Object>      b              = HashMultimap.create();
    private static final Set<SetMultimap<FullQualifiedName, Object>> propertyValues = new LinkedHashSet<>( 2 );

    static {
        registerModule( FullQualifedNameJacksonSerializer::registerWithMapper );
        registerModule( FullQualifedNameJacksonDeserializer::registerWithMapper );
        a.put( new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) ), "A" );
        a.put( new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) ), "A" );

        a.put( new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) ), "ABC" );

        b.put( new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) ), "100" );
        b.put( new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) ), "101" );

        b.put( new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 10 ),
                RandomStringUtils.randomAlphanumeric( 10 ) ), "102" );
        propertyValues.add( a );
        propertyValues.add( b );
    }

    @Override
    protected CreateEntityRequest getSampleData() {
        return new CreateEntityRequest(
                Optional.of( RandomStringUtils.randomAlphanumeric( 10 ) ),
                new FullQualifiedName(
                        RandomStringUtils.randomAlphanumeric( 10 ),
                        RandomStringUtils.randomAlphanumeric( 10 ) ),
                propertyValues,
                Optional.of( UUID.randomUUID() ),
                Optional.of( UUID.randomUUID() ) );
    }

    @Override
    protected Class<CreateEntityRequest> getClazz() {
        return CreateEntityRequest.class;
    }

}
