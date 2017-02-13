package com.dataloom.data.serializers;

import java.io.IOException;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class FullQualifedNameJacksonDeserializer extends StdDeserializer<FullQualifiedName> {
    private static final long serialVersionUID = 4245054290957537357L;

    protected FullQualifedNameJacksonDeserializer() {
        this( null );
    }

    public FullQualifedNameJacksonDeserializer( Class<FullQualifiedName> clazz ) {
        super( clazz );
    }

    public static void registerWithMapper( ObjectMapper mapper ) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer( FullQualifiedName.class, new FullQualifedNameJacksonDeserializer() );
        mapper.registerModule( module );
    }

    @Override
    public FullQualifiedName deserialize( JsonParser jp, DeserializationContext ctxt )
            throws IOException, JsonProcessingException {
        
        JsonNode node = jp.getCodec().readTree( jp );
        return new FullQualifiedName(
                node.get( SerializationConstants.NAMESPACE_FIELD ).asText(),
                node.get( SerializationConstants.NAME_FIELD ).asText() );
    }
}
