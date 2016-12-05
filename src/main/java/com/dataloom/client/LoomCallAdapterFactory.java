package com.dataloom.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoomCallAdapterFactory extends CallAdapter.Factory {
    private static final Logger logger = LoggerFactory.getLogger( LoomCallAdapterFactory.class );

    @Override
    public CallAdapter<?> get( Type returnType, Annotation[] annotations, Retrofit retrofit ) {
        return new CallAdapter<Object>() {
            @Override
            public Type responseType() {
                return returnType;
            }

            @Override
            public <R> Object adapt( Call<R> call ) {
                try {
                    Response<R> response = call.execute();
                    if ( response.code() != 200 ) {
                        logger.error( "Call failed with code {} and message {} and error body {}",
                                response.code(),
                                response.message(),
                                IOUtils.toString( response.errorBody().byteStream() ) );
                    }
                    return response.body();
                } catch ( IOException e ) {
                    return null;
                }
            }

        };
    }

}
