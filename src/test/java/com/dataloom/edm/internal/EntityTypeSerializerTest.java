package com.dataloom.edm.internal;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dataloom.data.serializers.FullQualifedNameJacksonDeserializer;
import com.dataloom.data.serializers.FullQualifedNameJacksonSerializer;
import com.dataloom.edm.type.EntityType;
import com.dataloom.mapstores.TestDataFactory;
import com.dataloom.serializer.AbstractJacksonSerializationTest;

public class EntityTypeSerializerTest extends AbstractJacksonSerializationTest<EntityType> {

    @BeforeClass
    public static void configureSerializer() {
        FullQualifedNameJacksonSerializer.registerWithMapper( mapper );
        FullQualifedNameJacksonDeserializer.registerWithMapper( mapper );
        FullQualifedNameJacksonSerializer.registerWithMapper( smile );
        FullQualifedNameJacksonDeserializer.registerWithMapper( smile );
    }

    @Override
    protected EntityType getSampleData() {
        return TestDataFactory.entityType();
    }

    @Override
    protected Class<EntityType> getClazz() {
        return EntityType.class;
    }

    @Test
    public void testIncludesCategory() throws IOException {
        String json = serialize( getSampleData() ).getJsonString();
        logger.debug( json );
        Assert.assertTrue( "Json must contain category property", json.contains( "category" ) );
    }
}