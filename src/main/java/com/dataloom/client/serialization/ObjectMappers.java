package com.dataloom.client.serialization;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.google.common.collect.Maps;

public final class ObjectMappers {
    public enum Mapper {
        YAML,
        SMILE,
        JSON
    }

    // TODO: Add options that for configuring serialization types supported.

    private static final Map<Mapper, ObjectMapper> mappers = Maps.newEnumMap( Mapper.class );

    static {
        mappers.put( Mapper.YAML, createYamlMapper() );
        mappers.put( Mapper.SMILE, createSmileMapper() );
        mappers.put( Mapper.JSON, createJsonMapper() );
    }

    private ObjectMappers() {}

    protected static ObjectMapper createYamlMapper() {
        ObjectMapper yamlMapper = new ObjectMapper( new YAMLFactory() );
        yamlMapper.registerModule( new GuavaModule() );
        yamlMapper.registerModule( new AfterburnerModule() );
        yamlMapper.registerModule( new JodaModule() );
        return yamlMapper;
    }

    protected static ObjectMapper createSmileMapper() {
        ObjectMapper smileMapper = new ObjectMapper( new SmileFactory() );
        smileMapper.registerModule( new GuavaModule() );
        smileMapper.registerModule( new AfterburnerModule() );
        smileMapper.registerModule( new JodaModule() );
        return smileMapper;
    }

    protected static ObjectMapper createJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule( new GuavaModule() );
        mapper.registerModule( new JodaModule() );
        mapper.registerModule( new AfterburnerModule() );
        mapper.registerModule( new JodaModule() );
        return mapper;
    }

    public static ObjectMapper getYamlMapper() {
        return ObjectMappers.getMapper( Mapper.YAML );
    }

    public static ObjectMapper getSmileMapper() {
        return ObjectMappers.getMapper( Mapper.SMILE );
    }

    public static ObjectMapper getJsonMapper() {
        return ObjectMappers.getMapper( Mapper.JSON );
    }

    public static ObjectMapper getMapper( Mapper type ) {
        return mappers.get( type );
    }
}
