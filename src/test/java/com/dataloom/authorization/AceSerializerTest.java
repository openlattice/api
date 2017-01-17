package com.dataloom.authorization;

import org.junit.BeforeClass;

import com.dataloom.authorization.Ace;
import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class AceSerializerTest extends BaseJacksonSerializationTest<Ace> {

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