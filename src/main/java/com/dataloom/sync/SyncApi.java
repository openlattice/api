package com.dataloom.sync;

import java.util.UUID;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SyncApi {

    String SERVICE            = "/datastore";
    String CONTROLLER         = "/sync";
    String BASE               = SERVICE + CONTROLLER;

    String ENTITY_SET_ID      = "entitySetId";

    String ENTITY_SET_ID_PATH = "{" + ENTITY_SET_ID + "}";
    String NEW                = "/new";

    /**
     * Generates a new sync id for an entity set.
     * 
     * @param entitySetId The id of the entity set to generate a new sync id for.
     * @return A new time-uuid generated in data source api.
     */
    @GET( BASE + "/" + ENTITY_SET_ID_PATH + NEW )
    UUID acquireSyncId(
            @Path( ENTITY_SET_ID ) UUID entitySetId );

    /**
     * Retrieves the latest sync id for the given entity set, and creates one if one doesn't exist yet
     * 
     * @param entitySetId The id of the entity set to load the latest sync id for.
     * @return The latest time-uuid for the entity set.
     */
    @GET( BASE + "/" + ENTITY_SET_ID_PATH )
    UUID getLatestSyncId(
            @Path( ENTITY_SET_ID ) UUID entitySetId );

}
