package com.dataloom.client;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.dataloom.client.serialization.SerializableSupplier;
import com.dataloom.mappers.ObjectMappers;
import com.dataloom.retrofit.LoomByteConverterFactory;
import com.dataloom.retrofit.LoomCallAdapterFactory;
import com.dataloom.retrofit.LoomJacksonConverterFactory;
import com.dataloom.retrofit.LoomStringConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class RetrofitFactory {
    private static final String BASE_URL         = "https://api.thedataloom.com/";
    private static final String STAGING_BASE_URL = "https://api.staging.thedataloom.com/";
    private static final String LOCAL_BASE_URL   = "http://localhost:8080/";
    private static final String TESTING_BASE_URL = "http://localhost:8080/";

    private RetrofitFactory() {}

    public static enum Environment {
        PRODUCTION( BASE_URL ),
        STAGING( STAGING_BASE_URL ),
        LOCAL( LOCAL_BASE_URL ),
        TESTING( TESTING_BASE_URL );

        private final String baseUrl;

        private Environment( String baseUrl ) {
            this.baseUrl = baseUrl;
        }

        public String getBaseUrl() {
            return baseUrl;
        }
    }

    public static final Retrofit newClient( SerializableSupplier<String> jwtToken ) {
        return newClient( Environment.PRODUCTION, jwtToken );
    }

    public static final Retrofit newClient( Environment environment, Supplier<String> jwtToken ) {
        OkHttpClient.Builder httpBuilder = okhttpClientWithLoomAuth( jwtToken );
        return decorateWithLoomFactories( createBaseLoomRetrofitBuilder( environment, httpBuilder ) ).build();
    }

    public static final Retrofit newClient( String baseUrl, Supplier<String> jwtToken ) {
        OkHttpClient.Builder httpBuilder = okhttpClientWithLoomAuth( jwtToken );
        return decorateWithLoomFactories( createBaseLoomRetrofitBuilder( baseUrl, httpBuilder.build() ) ).build();
    }

    public static final Retrofit newClient( Retrofit.Builder retrofitBuilder ) {
        return decorateWithLoomFactories( retrofitBuilder ).build();
    }

    public static final Retrofit.Builder createBaseLoomRetrofitBuilder(
            Environment environment,
            OkHttpClient.Builder httpBuilder ) {
        return createBaseLoomRetrofitBuilder( environment.getBaseUrl(), httpBuilder.build() );
    }

    public static final Retrofit.Builder createBaseLoomRetrofitBuilder(
            Environment environment,
            OkHttpClient httpClient ) {
        return createBaseLoomRetrofitBuilder( environment.getBaseUrl(), httpClient );
    }

    public static final Retrofit.Builder createBaseLoomRetrofitBuilder( String baseUrl, OkHttpClient httpClient ) {
        return new Retrofit.Builder().baseUrl( baseUrl ).client( httpClient );
    }

    public static final Retrofit.Builder decorateWithLoomFactories( Retrofit.Builder builder ) {
        return builder.addConverterFactory( new LoomByteConverterFactory() )
                .addConverterFactory( new LoomStringConverterFactory() )
                .addConverterFactory( new LoomJacksonConverterFactory( ObjectMappers.getJsonMapper() ) )
                .addCallAdapterFactory( new LoomCallAdapterFactory() );
    }

    public static final OkHttpClient.Builder okhttpClientWithLoomAuth( Supplier<String> jwtToken ) {
        return new OkHttpClient.Builder()
                .addInterceptor( chain -> chain
                        .proceed( chain.request().newBuilder().addHeader( "Authorization", "Bearer " + jwtToken.get() )
                                .build() ) )
                .readTimeout( 2, TimeUnit.MINUTES );
    }

}
