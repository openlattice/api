package com.dataloom.authorization;

import com.dataloom.serializer.AbstractJacksonSerializationTest;
import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;

public class AceSerializerTest extends AbstractJacksonSerializationTest<Ace> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected Ace getSampleData() {
        return TestDataFactory.ace();
    }

    @Override
    protected Class<Ace> getClazz() {
        return Ace.class;
    }
}