package com.dataloom.search;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchApi {
	
	String SEARCH = "search";
	String KEYWORD = "kw";
	String ENTITY_TYPE_ID = "eid";
	String PROPERTY_TYPE_ID = "pid";
	
	/** 
	 * The query, entityType, and propertyTypes params are all optional,
	 * but at least one must be specified otherwise an error will be thrown.
	 * All specified params are required to be present in each entity set returned.
	 * If entityType and propertyTypes are both specified, the propertyTypes param
	 * will be ignored.
	 * 
	 * @param query
	 * @param entityType
	 * @param propertyTypes
	 * @return JSON string of matching entity set metadata
	 */
	@GET( SEARCH )
	String executeQueryJson(
			@Query(KEYWORD) String query,
			@Query(ENTITY_TYPE_ID) UUID entityType,
			@Query(PROPERTY_TYPE_ID) Set<UUID> propertyTypes );
	
	/** 
	 * The query, entityType, and propertyTypes params are all optional,
	 * but at least one must be specified otherwise an error will be thrown.
	 * All specified params are required to be present in each entity set returned.
	 * If entityType and propertyTypes are both specified, the propertyTypes param
	 * will be ignored.
	 * 
	 * @param query
	 * @param entityType
	 * @param propertyTypes
	 * @return Iterable of {@code Map<String, Object>} where each map corresponds to matching entity set metadata
	 */
	@POST( SEARCH )
	Iterable<Map<String, Object>> executeQuery(
			@Query(KEYWORD) String query,
			@Query(ENTITY_TYPE_ID) UUID entityType,
			@Body Set<UUID> propertyTypes );

}
