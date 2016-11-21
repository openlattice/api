package com.dataloom.client;

import com.dataloom.client.serialization.SerializableSupplier;

public interface ApiFactoryFactory extends SerializableSupplier<ApiFactory> {
    default <T> T create( Class<T> clazz ) {
        return this.get().create( clazz );
    }
}
