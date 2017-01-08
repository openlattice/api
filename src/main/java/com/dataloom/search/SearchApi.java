package com.dataloom.search;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SearchApi {
	
	String SEARCH = "search";
	String QUERY = "query";
	String NAME = "name";
	String NAMESPACE = "namespace";
	String ENTITY_SET = "entitySet";
	String ENTITY_TYPE = "entityType";
	String PROPERTY_TYPE = "propertyType";
	
	String QUERY_PATH = "{" + QUERY + "}";
	
	@POST( SEARCH + "/" + QUERY_PATH )
	Object executeQuery(
			@Path(QUERY) String query,
			FullQualifiedName entityType,
			Set<FullQualifiedName> propertyTypes );

}
