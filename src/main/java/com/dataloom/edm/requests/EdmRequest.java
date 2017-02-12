package com.dataloom.edm.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EdmRequest {
    public static enum Action {
        ADD,
        REMOVE,
        REPLACE
    };

    private final Action    action;
    private final Set<UUID> propertyTypes;
    private final Set<UUID> entityTypes;

    @JsonCreator
    public EdmRequest(
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.PROPERTY_TYPES ) Set<UUID> propertyTypes,
            @JsonProperty( SerializationConstants.ENTITY_TYPES ) Set<UUID> entityTypes ) {
        this.action = action;
        this.propertyTypes = propertyTypes;
        this.entityTypes = entityTypes;
    }

    @JsonProperty( SerializationConstants.ACTION )
    public Action getAction() {
        return action;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPES )
    public Set<UUID> getPropertyTypes() {
        return propertyTypes;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPES )
    public Set<UUID> getEntityTypes() {
        return entityTypes;
    }

}
