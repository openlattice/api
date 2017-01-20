package com.dataloom.data;

import java.util.Map;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.requests.EntitySetSelection;
import com.google.common.collect.SetMultimap;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE                 = "/datastore";
    String CONTROLLER              = "/data";
    String BASE                    = SERVICE + CONTROLLER;
    
    public static enum FileType {
        json,
        csv;
    }

    /**
     * To discuss paths later; perhaps batch this with EdmApi paths
     */

    String HISTORICAL    = "historical";
    String ENTITY_DATA   = "entitydata";

    String SET_ID        = "setId";
    String SYNC_ID       = "syncId";
    String SET_ID_PATH   = "{" + SET_ID + "}";
    String SYNC_ID_PATH  = "{" + SYNC_ID + "}";

    String FILE_TYPE     = "fileType";

    @GET( BASE + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Iterable<SetMultimap<FullQualifiedName, Object>> getEntitySetData(
            @Path( SET_ID ) UUID entitySetId,
            @Query( FILE_TYPE ) FileType fileType );

    @POST( BASE + "/" + HISTORICAL + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Iterable<SetMultimap<FullQualifiedName, Object>> getEntitySetData(
            @Path( SET_ID ) UUID entitySetId,
            @Body EntitySetSelection req,
            @Query( FILE_TYPE ) FileType fileType );

    @POST( BASE + "/" + ENTITY_DATA + "/" + SET_ID_PATH + "/" + SYNC_ID_PATH )
    Void createEntityData(
            @Path( SET_ID ) UUID entitySetId,
            @Path( SYNC_ID ) UUID syncId,
            @Body Map<String, SetMultimap<UUID, Object>> entities );
}
