package com.dataloom.data.requests;


import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.dataloom.edm.validation.ValidateFullQualifiedName;
import com.dataloom.edm.validation.ValidateUUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupEntitiesRequest {
    @Valid
    private final Set<@ValidateFullQualifiedName FullQualifiedName>         entityTypes;
    //TODO Validate keys of the map
    private final Map<FullQualifiedName, Object> propertyTypeToValueMap;
    @ValidateUUID
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
