package com.dataloom.analysis;

import java.util.List;
import java.util.UUID;

import com.dataloom.analysis.requests.TopUtilizerDetails;
import com.google.common.collect.SetMultimap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnalysisApi {

    String SERVICE    = "/datastore";
    String CONTROLLER = "/analysis";
    String BASE       = SERVICE + CONTROLLER;

    public static enum FileType {
        json,
        csv;
    }

    String FILE_TYPE          = "fileType";

    String ENTITY_SET_ID      = "entitySetId";
    String NUM_RESULTS        = "numResults";
    String ENTITY_SET_ID_PATH = "/{" + ENTITY_SET_ID + "}";
    String NUM_RESULTS_PATH   = "/{" + NUM_RESULTS + "}";

    /**
     * Returns the top rows in the entity set, ordered by the sum of the number of items in each of the property types
     * provided Entity ids and count are included for each row.
     * 
     * @param entitySetId The id of the entity set to sort and return results for
     * @param numResults The number of results to return
     * @param topUtilizerDetails A list of objects each specifying an association type and at least one neighbor types
     *            to include in a top utilizers search, as well as an indication of the directionality. The results will
     *            be ordered by the total number of values across all property types provided (i.e. the sum of all the
     *            property types' value array size).
     * @return
     */
    @POST( BASE + ENTITY_SET_ID_PATH + NUM_RESULTS_PATH )
    Iterable<SetMultimap<Object, Object>> getTopUtilizers(
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Path( NUM_RESULTS ) int numResults,
            @Body List<TopUtilizerDetails> topUtilizerDetails,
            @Query( FILE_TYPE ) FileType fileType );

}
