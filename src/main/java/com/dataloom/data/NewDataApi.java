package com.dataloom.data;

import java.util.UUID;

import com.dataloom.data.internal.Entity;
import com.dataloom.data.requests.CreateEntityRequest;
import com.dataloom.data.requests.GetEntitySetRequest;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NewDataApi {
    /**
     * To discuss paths later; perhaps batch this with EdmApi paths
     */

    String CONTROLLER    = "data";
    String ENTITY_DATA   = "entitydata";
    String GET_DATA_PATH = "getData";

    String SET_ID        = "setId";
    String SET_ID_PATH   = "{" + SET_ID + "}";

    @GET( CONTROLLER + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Iterable<Entity> getEntitySetData( @Path( SET_ID ) UUID entitySetId );

    // TODO Should discuss what this path should be
    @POST( CONTROLLER + "/" + ENTITY_DATA + "/" + SET_ID_PATH + "/" + GET_DATA_PATH )
    Iterable<Entity> getEntitySetData(
            @Path( SET_ID ) UUID entitySetId,
            @Body GetEntitySetRequest req );

    // TODO Add an endpoint that takes POST request at Controller + ENTITY_DATA + SET_ID_PATH?
    @POST( CONTROLLER + "/" + ENTITY_DATA )
    Void createEntityData( @Body CreateEntityRequest req );
}
