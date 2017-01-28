package com.dataloom.authorization;

import com.dataloom.serializer.AbstractJacksonSerializationTest;
import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;

public class AclSerializerTest extends AbstractJacksonSerializationTest<Acl> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected Acl getSampleData() {
        return TestDataFactory.acl();
    }

    @Override
    protected Class<Acl> getClazz() {
        return Acl.class;
    }
}