package com.dataloom.datasource;

import retrofit2.http.*;

import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface DatasourceApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/datasource";
    String BASE       = SERVICE + CONTROLLER;

    String ID   = "id";
    String SYNC = "sync";

    @POST( BASE )
    UUID createOrUpdateDatasource( @Body LoomDatasource datasource );

    @GET( BASE + "/{" + ID + "}" )
    LoomDatasource getDatasource( @Path( ID ) UUID datasourceId );

    @DELETE( BASE + "/{" + ID + "}" )
    Void deleteDatasource( @Path( ID ) UUID datasourceId );

    @POST( BASE + "/{" + ID + "}" )
    UUID startSync( @Path( ID ) UUID datasourceId );

    @DELETE( BASE + "/{" + ID + "}/" + SYNC + "}" )
    Void signalSyncCompleted( @Path( ID ) UUID datasourceId, @Path( SYNC ) UUID syncId );

}
