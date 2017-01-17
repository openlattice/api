package com.dataloom.edm.internal;

import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class EdmDetailsSerializerTest extends BaseJacksonSerializationTest<EdmDetails> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected EdmDetails getSampleData() {
        return TestDataFactory.edmDetails();
    }

    @Override
    protected Class<EdmDetails> getClazz() {
        return EdmDetails.class;
    }
}