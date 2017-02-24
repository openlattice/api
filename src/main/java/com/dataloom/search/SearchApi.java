package com.dataloom.search;

import java.util.UUID;

import com.dataloom.edm.EntitySet;
import com.dataloom.search.requests.AdvancedSearch;
import com.dataloom.search.requests.Search;
import com.dataloom.search.requests.SearchResult;
import com.dataloom.search.requests.SearchTerm;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SearchApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE            = "/datastore";
    String CONTROLLER         = "/search";
    String BASE               = SERVICE + CONTROLLER;
    String ENTITY_SET_ID      = "entitySetId";
    String ENTITY_SET_ID_PATH = "/{" + ENTITY_SET_ID + "}";

    /*
     * Normal params
     */
    String POPULAR            = "/popular";
    String ORGANIZATIONS      = "/organizations";
    String ADVANCED           = "/advanced";
    String KEYWORD            = "kw";
    String ENTITY_TYPE_ID     = "eid";
    String PROPERTY_TYPE_ID   = "pid";

    /**
     * The query, entityType, and propertyTypes params are all optional, but at least one must be specified otherwise an
     * error will be thrown. All specified params are required to be present in each entity set returned. If entityType
     * and propertyTypes are both specified, the propertyTypes param will be ignored.
     *
     * @param search A JSON object that contains between three and five parameters. Required parameters are "start" and
     *            "maxHits, which specify the hit number to start returning results on for paging and the maximum number
     *            of hits to return. Optional parameters are "query" (specifies the keywords used to perform the
     *            search), "eid" (UUID of the entity type of the entity sets that will be returned), and "pid" (a set of
     *            UUIDs of property types belonging to the entity sets that will be returned). All three of these
     *            parameters are optional, but at least one must be specified otherwise an error will be thrown. If eid
     *            and pid are both specified, the pid param will be ignored.
     * @return A search result object, containing the total number of hits for the given query, and the hits themselves
     */
    @POST( BASE )
    SearchResult executeEntitySetKeywordQuery( @Body Search search );

    @GET( BASE + POPULAR )
    Iterable<EntitySet> getPopularEntitySet();

    /**
     * Executes a search over the data of a given entity set to find rows that match the search term
     * 
     * @param entitySetId The id of the entity set the search will be executed within
     * @param searchTerm A JSON object that contains three parameters: "start", which specifies the hit number to start
     *            returning results on for paging, "maxHits", which specifies the maximum number of hits to return, and
     *            "searchTerm", which is the search term results will match on.
     * @return A search result object, containing the total number of hits for the given query, and the hits themselves
     */
    @POST( BASE + ENTITY_SET_ID_PATH )
    SearchResult executeEntitySetDataQuery(
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Body SearchTerm searchTerm );

    @POST( BASE + ADVANCED + ENTITY_SET_ID_PATH )
    SearchResult executeAdvancedEntitySetDataQuery(
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Body AdvancedSearch search );

    /**
     * Executes a search over all organizations to find ones that match the given search term
     * 
     * @param searchTerm A JSON object that contains three parameters: "start", which specifies the hit number to start
     *            returning results on for paging, "maxHits", which specifies the maximum number of hits to return, and
     *            "searchTerm", which is the search term results will match on.
     * @return A search result object, containing the total number of hits for the given query, and the hits themselves
     */
    @POST( BASE + ORGANIZATIONS )
    SearchResult executeOrganizationSearch( @Body SearchTerm searchTerm );

}
