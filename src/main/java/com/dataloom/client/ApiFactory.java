package com.dataloom.client;

import com.dataloom.client.serialization.SerializableFunction;

public interface ApiFactory extends SerializableFunction<Class<?>, Object> {
    @SuppressWarnings( "unchecked" )
    default <T> T create( Class<T> clazz ) {
        Object api =  this.apply( clazz );
        return (T) api;
    }
}
