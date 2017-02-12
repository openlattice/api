package com.dataloom.edm.internal;

import com.dataloom.edm.type.PropertyType;
import com.dataloom.serializer.AbstractJacksonSerializationTest;
import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;

public class EntitySetSerializerTest extends AbstractJacksonSerializationTest<PropertyType> {

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