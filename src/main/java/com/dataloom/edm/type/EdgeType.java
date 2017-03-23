package com.dataloom.edm.type;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class EdgeType {

    private Optional<EntityType>          edgeEntityType;
    private LinkedHashSet<UUID> src;
    private LinkedHashSet<UUID> dest;
    private boolean             bidirectional;

    @JsonCreator
    public EdgeType(
            @JsonProperty( SerializationConstants.ENTITY_TYPE ) Optional<EntityType> edgeEntityType,
            @JsonProperty( SerializationConstants.SRC ) LinkedHashSet<UUID> src,
            @JsonProperty( SerializationConstants.DEST ) LinkedHashSet<UUID> dest,
            @JsonProperty( SerializationConstants.BIDIRECTIONAL ) boolean bidirectional ) {
        this.edgeEntityType = edgeEntityType;
        this.src = src;
        this.dest = dest;
        this.bidirectional = bidirectional;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE )
    public EntityType getEdgeEntityType() {
        return edgeEntityType.orNull();
    }

    @JsonProperty( SerializationConstants.SRC )
    public Set<UUID> getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DEST )
    public Set<UUID> getDest() {
        return dest;
    }

    @JsonProperty( SerializationConstants.BIDIRECTIONAL )
    public boolean isBidirectional() {
        return bidirectional;
    }

}
