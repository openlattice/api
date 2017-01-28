package com.dataloom.client;

import com.dataloom.authorization.PermissionsApi;
import com.dataloom.client.RetrofitFactory.Environment;
import com.dataloom.client.serialization.SerializableSupplier;
import com.dataloom.data.DataApi;
import com.dataloom.edm.EdmApi;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;

import java.util.concurrent.ExecutionException;

public class LoomClient implements ApiFactoryFactory {

    private static final long serialVersionUID = -5757911484718872922L;

    private static final Logger logger = LoggerFactory
            .getLogger( LoomClient.class );
    private final ApiFactoryFactory retrofitSupplier;
    private transient Supplier<ApiFactory>           restAdapter = null;
    private transient LoadingCache<Class<?>, Object> apiCache    = null;

    public LoomClient( Environment environment, SerializableSupplier<String> jwtToken ) {
        this( () -> {
            final Retrofit retrofit = RetrofitFactory.newClient( environment, jwtToken );
            return (ApiFactory) retrofit::create;
        } );
    }

    public LoomClient( SerializableSupplier<String> jwtToken ) {
        this( () -> {
            final Retrofit retrofit = RetrofitFactory.newClient( jwtToken );
            return (ApiFactory) retrofit::create;
        } );
    }

    public LoomClient( ApiFactoryFactory retrofitSupplier ) {
        this.retrofitSupplier = retrofitSupplier;
        logger.info( "Loom client ready!" );
    }

    public static SerializableSupplier<LoomClient> getSerializableSupplier( SerializableSupplier<String> jwtToken ) {
        return () -> new LoomClient( jwtToken );
    }

    public static SerializableSupplier<LoomClient> getSerializableSupplier(
            Environment environment,
            SerializableSupplier<String> jwtToken ) {
        return () -> new LoomClient( environment, jwtToken );
    }

    public DataApi getDataApi() throws ExecutionException {
        return (DataApi) get().create( DataApi.class );
    }

    public PermissionsApi getPermissionsApi() throws ExecutionException {
        return (PermissionsApi) get().create( PermissionsApi.class );
    }

    public EdmApi getEdmApi() throws ExecutionException {
        return (EdmApi) get().create( EdmApi.class );
    }

    public ApiFactory get() {
        if ( apiCache == null ) {
            apiCache = CacheBuilder.newBuilder()
                    .maximumSize( 100 )
                    .initialCapacity( 10 )
                    .build( new CacheLoader<Class<?>, Object>() {
                        private final Supplier<ApiFactory> apiFactory = Suppliers.memoize( retrofitSupplier::get );

                        @Override
                        public Object load( Class<?> key ) throws Exception {
                            return apiFactory.get().create( key );
                        }
                    } );
        }

        return apiCache::getUnchecked;
    }
}
