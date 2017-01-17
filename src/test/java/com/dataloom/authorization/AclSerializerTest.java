package com.dataloom.authorization;

import org.junit.BeforeClass;

import com.dataloom.authorization.Acl;
import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class AclSerializerTest extends BaseJacksonSerializationTest<Acl> {

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