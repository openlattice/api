package com.dataloom.data;

import java.util.Map;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.requests.EntitySetSelection;
import com.google.common.collect.SetMultimap;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/data";
    String BASE       = SERVICE + CONTROLLER;

    public static enum FileType {
        json,
        csv;
    }

    /**
     * To discuss paths later; perhaps batch this with EdmApi paths
     */

    String HISTORICAL   = "historical";
    String ENTITY_DATA  = "entitydata";

    String TICKET       = "ticket";
    String SET_ID       = "setId";
    String SYNC_ID      = "syncId";

    String SET_ID_PATH  = "{" + SET_ID + "}";
    String SYNC_ID_PATH = "{" + SYNC_ID + "}";
    String TICKET_PATH  = "{" + TICKET + "}";

    String FILE_TYPE    = "fileType";
    String TOKEN        = "token";

    @POST( BASE + "/" + TICKET + "/" + SET_ID_PATH + "/" + SYNC_ID_PATH )
    UUID acquireSyncTicket( @Path( SET_ID ) UUID entitySetId, @Path( SYNC_ID ) UUID syncId );

    @DELETE( BASE + "/" + TICKET + "/" + TICKET_PATH )
    Void releaseSyncTicket( @Path( TICKET ) UUID ticket );

    @PATCH( BASE + "/" + ENTITY_DATA + "/" + TICKET_PATH + "/" + SYNC_ID_PATH )
    Void storeEntityData(
            @Path( TICKET ) UUID ticket,
            @Path( SYNC_ID ) UUID syncId,
            @Body Map<String, SetMultimap<UUID, Object>> entities );

    @GET( BASE + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Iterable<SetMultimap<FullQualifiedName, Object>> loadEntitySetData(
            @Path( SET_ID ) UUID entitySetId,
            @Query( FILE_TYPE ) FileType fileType,
            @Query( TOKEN ) String token );

    /**
     * 
     * @param entitySetId
     * @param req If syncId is not specified in the request, will retrieve the data from the latest syncId's. If
     *            selectedProperties are not specified, all readable properties will be fetched.
     * @param fileType
     * @return
     */
    @POST( BASE + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Iterable<SetMultimap<FullQualifiedName, Object>> loadEntitySetData(
            @Path( SET_ID ) UUID entitySetId,
            @Body EntitySetSelection req,
            @Query( FILE_TYPE ) FileType fileType );

    /**
     * Creates a new set of entities.
     * 
     * @param entitySetId The id of the entity set to write to.
     * @param syncId A time-uuid retrieved from data source api.
     * @param entities A map describe the entities to create. Each key will be used as the entity id and must be unique
     *            and stable across repeated integrations of data. If either constraint is violated then data may be
     *            overwritten or duplicated.
     * @return
     */
    @PUT( BASE + "/" + ENTITY_DATA + "/" + SET_ID_PATH + "/" + SYNC_ID_PATH )
    Void createEntityData(
            @Path( SET_ID ) UUID entitySetId,
            @Path( SYNC_ID ) UUID syncId,
            @Body Map<String, SetMultimap<UUID, Object>> entities );

    /**
     * Creates a new set of entities for the specified entity set's latest sync id.
     * 
     * @param entitySetId The id of the entity set to write to.
     * @param entities A map describe the entities to create. Each key will be used as the entity id and must be unique
     *            and stable across repeated integrations of data. If either constraint is violated then data may be
     *            overwritten or duplicated.
     * @return
     */
    @PUT( BASE + "/" + ENTITY_DATA + "/" + SET_ID_PATH )
    Void createEntityData(
            @Path( SET_ID ) UUID entitySetId,
            @Body Map<String, SetMultimap<UUID, Object>> entities );

    /**
     * Generates a new sync id for an entity set.
     * 
     * @param entitySetId The id of the entity set to generate a new sync id for.
     * @return A new time-uuid generated in data source api.
     */
    @GET( BASE + "/" + SET_ID_PATH + "/syncId" )
    UUID getNewSyncId(
            @Path( SET_ID ) UUID entitySetId );

}
