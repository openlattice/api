package com.dataloom.edm.internal;

import com.dataloom.edm.EdmDetails;
import com.dataloom.serializer.AbstractJacksonSerializationTest;
import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;

public class EdmDetailsSerializerTest extends AbstractJacksonSerializationTest<EdmDetails> {

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