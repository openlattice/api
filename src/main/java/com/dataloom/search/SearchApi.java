package com.dataloom.search;

import java.util.List;
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
		
	@GET( SEARCH )
	String executeQueryJson(
			@Query(KEYWORD) String query,
			@Query(ENTITY_TYPE_ID) UUID entityType,
			@Query(PROPERTY_TYPE_ID) Set<UUID> propertyTypes );
	
	@POST( SEARCH )
	List<Map<String, Object>> executeQuery(
			@Query(KEYWORD) String query,
			@Query(ENTITY_TYPE_ID) UUID entityType,
			@Body Set<UUID> propertyTypes );

}
