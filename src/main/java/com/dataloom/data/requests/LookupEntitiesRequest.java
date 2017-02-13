package com.dataloom.data.requests;


import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupEntitiesRequest {
    private final Set<FullQualifiedName>         entityTypes;
    private final Map<FullQualifiedName, Object> propertyTypeToValueMap;
    private final UUID                           userId;

    public LookupEntitiesRequest(
            UUID userId,
            Set<FullQualifiedName> entityTypes,
            Map<FullQualifiedName, Object> propertyTypeToValueMap ) {
        this.entityTypes = entityTypes;
        this.propertyTypeToValueMap = propertyTypeToValueMap;
        this.userId = userId;
    }

    public Set<FullQualifiedName> getEntityTypes() {
        return entityTypes;
    }

    public Map<FullQualifiedName, Object> getPropertyTypeToValueMap() {
        return propertyTypeToValueMap;
    }

    public UUID getUserId() {
        return userId;
    }
    
    @JsonCreator
    public static LookupEntitiesRequest newLookupEntitiesRequest(
    		@JsonProperty( SerializationConstants.USER_ID ) UUID userId,
    		@JsonProperty( SerializationConstants.TYPE_FIELD ) Set<FullQualifiedName> entityTypes,
    		@JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Map<String, Object> propertyMapInString) {
    	//Create propertyValues with FullQualifiedName as key
    	Map<FullQualifiedName, Object> propertyMapInFQN = propertyMapInString.entrySet()
    			.stream()
    			.collect(Collectors.toMap(
    						entry -> new FullQualifiedName( entry.getKey() ),
    						entry -> entry.getValue()
    					)
    			);
    	
        return new LookupEntitiesRequest(
        		userId,
        		entityTypes,
        		propertyMapInFQN
        		);
    } 

}
