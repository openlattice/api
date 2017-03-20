package com.dataloom.analysis;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.SetMultimap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnalysisApi {

    String SERVICE            = "/datastore";
    String CONTROLLER         = "/analysis";
    String BASE               = SERVICE + CONTROLLER;
    String ENTITY_SET_ID      = "entitySetId";
    String NUM_RESULTS        = "numResults";
    String ENTITY_SET_ID_PATH = "/{" + ENTITY_SET_ID + "}";
    String NUM_RESULTS_PATH   = "/{" + NUM_RESULTS + "}";

    /**
     * Returns the top rows in the entity set, ordered by the sum of the number of items in each of the property types
     * provided
     * 
     * @param entitySetId The id of the entity set to sort and return results for
     * @param numResults The number of results to return
     * @param propertyTypeIds A set of property types which may have multiple values in an entity set. The results will
     *            be ordered by the total number of values across all property types provided (i.e. the sum of all the
     *            property types' value array size).
     * @return
     */
    @POST( BASE + ENTITY_SET_ID_PATH + NUM_RESULTS_PATH )
    List<SetMultimap<UUID, Object>> getTopUtilizers(
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Path( NUM_RESULTS ) int numResults,
            @Body Set<UUID> propertyTypeIds );

}