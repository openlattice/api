package com.dataloom.edm.internal;

import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class PropertyTypeSerializerTest extends BaseJacksonSerializationTest<EntitySet> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected EntitySet getSampleData() {
        return TestDataFactory.entitySet();
    }

    @Override
    protected Class<EntitySet> getClazz() {
        return EntitySet.class;
    }
}