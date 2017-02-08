package com.dataloom.search;

import com.dataloom.edm.internal.EntitySet;
import com.dataloom.search.requests.SearchRequest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.Map;
import java.util.UUID;

public interface SearchApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE          = "/datastore";
    String CONTROLLER       = "/search";
    String BASE             = SERVICE + CONTROLLER;
    String ENTITY_SET_ID    = "entitySetId";
    String ENTITY_SET_ID_PATH = "/{" + ENTITY_SET_ID + "}";

    String SEARCH_JAVA      = "/java";
    /*
     * Normal params
     */
    String POPULAR          = "/popular";
    String KEYWORD          = "kw";
    String ENTITY_TYPE_ID   = "eid";
    String PROPERTY_TYPE_ID = "pid";

    /**
     * The query, entityType, and propertyTypes params are all optional, but at least one must be specified otherwise an
     * error will be thrown. All specified params are required to be present in each entity set returned. If entityType
     * and propertyTypes are both specified, the propertyTypes param will be ignored.
     *
     * @param request A JSON object that contains up to three parameters: "query" (specifies the keywords used to
     *            perform the search), "eid" (UUID of the entity type of the entity sets that will be returned), and
     *            "pid" (a set of UUIDs of property types belonging to the entity sets that will be returned). All three
     *            of these parameters are optional, but at least one must be specified otherwise an error will be
     *            thrown. If eid and pid are both specified, the pid param will be ignored.
     * @return JSON string of matching entity set metadata
     */
    @POST( BASE )
    String executeQueryJson( @Body SearchRequest request );

    /**
     * The query, entityType, and propertyTypes params are all optional, but at least one must be specified otherwise an
     * error will be thrown. All specified params are required to be present in each entity set returned. If entityType
     * and propertyTypes are both specified, the propertyTypes param will be ignored.
     *
     * @param request A JSON object that contains up to three parameters: "query" (specifies the keywords used to
     *            perform the search), "eid" (UUID of the entity type of the entity sets that will be returned), and
     *            "pid" (a set of UUIDs of property types belonging to the entity sets that will be returned). All three
     *            of these parameters are optional, but at least one must be specified otherwise an error will be
     *            thrown. If eid and pid are both specified, the pid param will be ignored.
     * @return JSON string of matching entity set metadata
     */
    @POST( BASE + SEARCH_JAVA )
    Iterable<Map<String, Object>> executeQuery( @Body SearchRequest request );

    @GET( BASE + POPULAR )
    Iterable<EntitySet> getPopularEntitySet();
    
    @POST ( BASE + ENTITY_SET_ID_PATH )
    String executeEntitySetDataQuery(
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Body String searchTerm );
}
