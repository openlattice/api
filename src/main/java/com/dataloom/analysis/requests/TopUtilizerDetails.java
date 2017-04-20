package com.dataloom.analysis.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

public class TopUtilizerDetails {
    private final UUID      associationTypeId;
    private final Set<UUID> neighborTypeIds;
    private final boolean   utilizerIsSrc;

    @JsonCreator
    public TopUtilizerDetails(
            @JsonProperty( SerializationConstants.ASSOCIATION_TYPE_ID ) UUID associationTypeId,
            @JsonProperty( SerializationConstants.NEIGHBOR_TYPE_IDS ) Set<UUID> neighborTypeIds,
            @JsonProperty( SerializationConstants.UTILIZER_IS_SRC ) boolean utilizerIsSrc ) {
        Preconditions.checkNotNull( associationTypeId, "Association type id cannot be null." );
        Preconditions.checkNotNull( neighborTypeIds, "Neighbor type ids cannot be null." );
        Preconditions.checkArgument( neighborTypeIds.size() > 0, "Neighbor type ids cannot be empty." );

        this.associationTypeId = associationTypeId;
        this.neighborTypeIds = neighborTypeIds;
        this.utilizerIsSrc = utilizerIsSrc;
    }

    @JsonProperty( SerializationConstants.ASSOCIATION_TYPE_ID )
    public UUID getAssociationTypeId() {
        return associationTypeId;
    }

    @JsonProperty( SerializationConstants.NEIGHBOR_TYPE_IDS )
    public Set<UUID> getNeighborTypeIds() {
        return neighborTypeIds;
    }

    @JsonProperty( SerializationConstants.UTILIZER_IS_SRC )
    public boolean getUtilizerIsSrc() {
        return utilizerIsSrc;
    }

}
