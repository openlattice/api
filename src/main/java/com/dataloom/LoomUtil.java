package com.dataloom;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataloom.mappers.ObjectMappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.SetMultimap;

public class LoomUtil {
    private static final Encoder encoder = Base64.getEncoder();
    private static final Logger  logger  = LoggerFactory
            .getLogger( LoomUtil.class );

    private LoomUtil() {}

    public static String generateDefaultEntityId( List<UUID> keys, SetMultimap<UUID, Object> entityDetails ) {
        return generateDefaultEntityId(
                Preconditions.checkNotNull( keys, "Key properties must be configured for entity id generation." )
                        .stream(),
                entityDetails );
    }

    public static String generateDefaultEntityId( Stream<UUID> keys, SetMultimap<UUID, Object> entityDetails ) {
        String entityId = keys.map( entityDetails::get )
                .map( LoomUtil::joinObjectsAsString )
                .map( LoomUtil::toUtf8Bytes )
                .map( encoder::encodeToString )
                .collect( Collectors.joining( "," ) );
        return ( entityId.length() == 0 ) ? UUID.randomUUID().toString() : entityId;
    }

    private static String joinObjectsAsString( Set<Object> s ) {
        return s.stream()
                .map( LoomUtil::toBytes )
                .map( ByteBuffer::wrap )
                .sorted()
                .map( ByteBuffer::array )
                .map( encoder::encodeToString )
                .collect( Collectors.joining( "," ) );
    }

    private static byte[] toBytes( Object o ) {
        if ( o instanceof String ) {
            return toUtf8Bytes( (String) o );
        }
        try {
            return ObjectMappers.getJsonMapper().writeValueAsBytes( o );
        } catch ( JsonProcessingException e ) {
            logger.error( "Unable to serialize object for building entity id", e );
            return new byte[ 0 ];
        }
    }

    private static byte[] toUtf8Bytes( String s ) {
        return s.getBytes( Charsets.UTF_8 );
    }
}
