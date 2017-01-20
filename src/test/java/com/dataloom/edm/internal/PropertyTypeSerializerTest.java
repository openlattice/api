package com.dataloom.edm.internal;

import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class PropertyTypeSerializerTest extends BaseJacksonSerializationTest<PropertyType> {
    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected PropertyType getSampleData() {
        return TestDataFactory.propertyType();
    }

    @Override
    protected Class<PropertyType> getClazz() {
        return PropertyType.class;
    }
}
