package com.dataloom.edm.internal;

import org.junit.BeforeClass;

import com.dataloom.authorization.AclKeyPathFragment;
import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.serializer.BaseJacksonSerializationTest;

public class AclKeyPathFragmentSerializerTest extends BaseJacksonSerializationTest<AclKeyPathFragment> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected AclKeyPathFragment getSampleData() {
        return TestDataFactory.aclKeyPathFragment();
    }

    @Override
    protected Class<AclKeyPathFragment> getClazz() {
        return AclKeyPathFragment.class;
    }
}