package com.dataloom.edm.internal;

import org.junit.BeforeClass;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.mapstores.TestDataFactory;
import com.dataloom.organization.Organization;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class OrganizationSerializerTest extends BaseJacksonSerializationTest<Organization> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected Organization getSampleData() {
        return TestDataFactory.organization();
    }

    @Override
    protected Class<Organization> getClazz() {
        return Organization.class;
    }
}