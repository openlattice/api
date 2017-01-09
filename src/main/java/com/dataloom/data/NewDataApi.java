package com.dataloom.data;

import java.util.Map;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.requests.GetEntitySetRequest;
import com.google.common.collect.SetMultimap;

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
    String SYNC_ID        = "syncId";
    String SET_ID_PATH   = "{" + SET_ID + "}";
    String SYNC_ID_PATH   = "{" + SYNC_ID + "}";

    @GET( CONTROLLER + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Iterable<SetMultimap<FullQualifiedName, Object>> getEntitySetData( @Path( SET_ID ) UUID entitySetId );

    // TODO Should discuss what this path should be
    @POST( CONTROLLER + "/" + ENTITY_DATA + "/" + SET_ID_PATH + "/" + GET_DATA_PATH )
    Iterable<SetMultimap<FullQualifiedName, Object>> getEntitySetData(
            @Path( SET_ID ) UUID entitySetId,
            @Body GetEntitySetRequest req );

    @POST( CONTROLLER + "/" + ENTITY_DATA + "/" + SET_ID_PATH + "/" + SYNC_ID_PATH )
    Void createEntityData( @Path( SET_ID ) UUID entitySetId, @Path( SYNC_ID ) UUID syncId, @Body Map<String, SetMultimap<UUID, Object>> entities );
}
