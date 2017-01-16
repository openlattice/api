package com.dataloom.search;

import java.util.Map;

import com.dataloom.search.requests.SearchRequest;

import retrofit2.http.Body;
import retrofit2.http.GET;

public interface SearchApi {

    String SEARCH           = "search";
    String SEARCH_JAVA      = "searchJava";
    String KEYWORD          = "kw";
    String ENTITY_TYPE_ID   = "eid";
    String PROPERTY_TYPE_ID = "pid";

    /**
     * The query, entityType, and propertyTypes params are all optional,
     * but at least one must be specified otherwise an error will be thrown.
     * All specified params are required to be present in each entity set returned.
     * If entityType and propertyTypes are both specified, the propertyTypes param
     * will be ignored.
     * 
     * @param request A JSON object that contains up to three parameters: "query" (specifies the keywords used to perform
     * the search), "eid" (UUID of the entity type of the entity sets that will be returned), and "pid" (a set of UUIDs
     * of property types belonging to the entity sets that will be returned). All three of these parameters are optional,
     * but at least one must be specified otherwise an error will be thrown. If eid and pid are both specified, the pid
     * param will be ignored.
     * @return JSON string of matching entity set metadata
     */
    @GET( SEARCH )
    String executeQueryJson( @Body SearchRequest request );

    /**
     * The query, entityType, and propertyTypes params are all optional,
     * but at least one must be specified otherwise an error will be thrown.
     * All specified params are required to be present in each entity set returned.
     * If entityType and propertyTypes are both specified, the propertyTypes param
     * will be ignored.
     * 
     * @param request A JSON object that contains up to three parameters: "query" (specifies the keywords used to perform
     * the search), "eid" (UUID of the entity type of the entity sets that will be returned), and "pid" (a set of UUIDs
     * of property types belonging to the entity sets that will be returned). All three of these parameters are optional,
     * but at least one must be specified otherwise an error will be thrown. If eid and pid are both specified, the pid
     * param will be ignored.
     * @return JSON string of matching entity set metadata
     */
    @GET( SEARCH_JAVA )
    Iterable<Map<String, Object>> executeQuery( @Body SearchRequest request );

}
