package com.dataloom.edm.type;

import java.util.LinkedHashSet;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociationDetails {
    private LinkedHashSet<EntityType> srcEntityTypes;
    private LinkedHashSet<EntityType> dstEntityTypes;
    private boolean                   bidirectional;

    public AssociationDetails(
            @JsonProperty( SerializationConstants.SRC ) LinkedHashSet<EntityType> srcEntityTypes,
            @JsonProperty( SerializationConstants.DEST ) LinkedHashSet<EntityType> dstEntityTypes,
            @JsonProperty( SerializationConstants.BIDIRECTIONAL ) boolean bidirectional ) {
        this.srcEntityTypes = srcEntityTypes;
        this.dstEntityTypes = dstEntityTypes;
        this.bidirectional = bidirectional;
    }

    @JsonProperty( SerializationConstants.SRC )
    public LinkedHashSet<EntityType> getSrcEntityTypes() {
        return srcEntityTypes;
    }

    @JsonProperty( SerializationConstants.DEST )
    public LinkedHashSet<EntityType> getDstEntityTypes() {
        return dstEntityTypes;
    }

    @JsonProperty( SerializationConstants.BIDIRECTIONAL )
    public boolean isBidirectional() {
        return bidirectional;
    }

}
