package com.dataloom.edm.internal;

import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.kryptnostic.rhizome.hazelcast.serializers.BaseJacksonSerializationTest;
import com.kryptnostic.rhizome.registries.ObjectMapperRegistry;

public class EntityTypeSerializerTest extends BaseJacksonSerializationTest<EntityType> {

    @BeforeClass
    public static void configureSerializer() {
        ObjectMapperRegistry.foreach( FullQualifedNameJacksonSerializer::registerWithMapper );
        ObjectMapperRegistry.foreach( FullQualifedNameJacksonDeserializer::registerWithMapper );
    }

    @Override
    protected EntityType getSampleData() {
        return TestDataFactory.entityType();
    }

    @Override
    protected Class<EntityType> getClazz() {
        return EntityType.class;
    }
}