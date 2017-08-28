package com.dataloom.linking.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.edm.set.LinkingEntitySet;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkingRequest {
    private final LinkingEntitySet linkingEntitySet;
    private final Set<UUID>        resultPropertyTypeIds;

    @JsonCreator
    public LinkingRequest(
            @JsonProperty( SerializationConstants.LINKING_ENTITY_SET_FIELD ) LinkingEntitySet linkingEntitySet,
            @JsonProperty( SerializationConstants.PROPERTY_TYPE_ID_LIST ) Set<UUID> resultPropertyTypeIds ) {
        this.linkingEntitySet = linkingEntitySet;
        this.resultPropertyTypeIds = resultPropertyTypeIds;
    }

    @JsonProperty( SerializationConstants.LINKING_ENTITY_SET_FIELD )
    public LinkingEntitySet getLinkingEntitySet() {
        return linkingEntitySet;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPE_ID_LIST )
    public Set<UUID> getResultPropertyTypeIds() {
        return resultPropertyTypeIds;
    }
}
