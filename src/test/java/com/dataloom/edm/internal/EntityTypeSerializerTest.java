package com.dataloom.edm.internal;

import com.dataloom.edm.type.EntityType;
import com.dataloom.serializer.AbstractJacksonSerializationTest;
import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;

public class EntityTypeSerializerTest extends AbstractJacksonSerializationTest<EntityType> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
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