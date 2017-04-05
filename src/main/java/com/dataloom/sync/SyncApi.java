package com.dataloom.sync;

import java.util.UUID;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SyncApi {

    String SERVICE            = "/datastore";
    String CONTROLLER         = "/sync";
    String BASE               = SERVICE + CONTROLLER;

    String ENTITY_SET_ID      = "entitySetId";
    String SYNC_ID            = "syncId";

    String ENTITY_SET_ID_PATH = "/{" + ENTITY_SET_ID + "}";
    String SYNC_ID_PATH       = "/{" + SYNC_ID + "}";
    String NEW                = "/new";

    /**
     * Generates a new sync id for an entity set. If the entity set has no latest sync id, the new one is set as latest.
     * 
     * @param entitySetId The id of the entity set to generate a new sync id for.
     * @return A new time-uuid generated in data source api.
     */
    @GET( BASE + "/" + ENTITY_SET_ID_PATH + NEW )
    UUID acquireSyncId(
            @Path( ENTITY_SET_ID ) UUID entitySetId );

    /**
     * Retrieves the latest sync id for the given entity set
     * 
     * @param entitySetId The id of the entity set to load the latest sync id for.
     * @return The latest time-uuid for the entity set.
     */
    @GET( BASE + "/" + ENTITY_SET_ID_PATH )
    UUID getLatestSyncId( @Path( ENTITY_SET_ID ) UUID entitySetId );

    /**
     * Sets the latest syncId for the entity set to the specified syncId. By default, reads for the entity set will come
     * from the latest syncId, and writes will be written to the latest sync, unless otherwise specified.
     * 
     * @param entitySetId The id of the entity set to set the latest sync id for.
     * @param syncId the sync id that will be set as latest
     */
    @POST( BASE + ENTITY_SET_ID_PATH + SYNC_ID_PATH )
    Void setLatestSyncId( @Path( ENTITY_SET_ID ) UUID entitySetId, @Path( SYNC_ID ) UUID syncId );

}
