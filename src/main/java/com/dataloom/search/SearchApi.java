package com.dataloom.search;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE                 = "/datastore";
    String CONTROLLER              = "/search";
    String BASE                    = SERVICE + CONTROLLER;
    
    /*
     * Normal params 
     */
    String SEARCH_JAVA      = "/java";
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
     * @param query         An optional parameter the specifies they keywords used to perform the search.
     * @param entityType    An optional parameter specifying the entity type of the entity sets that will be returned.
     * @param propertyTypes An optional parameter specifying the property types of the entity sets that will be returned.
     * @return JSON string of matching entity set metadata
     */
    @POST( BASE )
    String executeQueryJson(
            @Query( KEYWORD ) String query,
            @Query( ENTITY_TYPE_ID ) UUID entityType,
            @Body Set<UUID> propertyTypes );

    /**
     * The query, entityType, and propertyTypes params are all optional,
     * but at least one must be specified otherwise an error will be thrown.
     * All specified params are required to be present in each entity set returned.
     * If entityType and propertyTypes are both specified, the propertyTypes param
     * will be ignored.
     *
     * @param query         An optional parameter the specifies they keywords used to perform the search.
     * @param entityType    An optional parameter specifying the entity type of the entity sets that will be returned.
     * @param propertyTypes An optional parameter specifying the property types of the entity sets that will be returned.
     * @return Iterable of {@code Map<String, Object>} where each map corresponds to matching entity set metadata
     */
    @POST( BASE + SEARCH_JAVA )
    Iterable<Map<String, Object>> executeQuery(
            @Query( KEYWORD ) String query,
            @Query( ENTITY_TYPE_ID ) UUID entityType,
            @Body Set<UUID> propertyTypes );

}
