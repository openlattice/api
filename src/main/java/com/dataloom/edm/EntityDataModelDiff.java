package com.dataloom.edm;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class EntityDataModelDiff {

    private final EntityDataModel           diff;
    private final Optional<EntityDataModel> conflicts;

    @JsonCreator
    public EntityDataModelDiff(
            @JsonProperty( SerializationConstants.DIFF ) EntityDataModel diff,
            @JsonProperty( SerializationConstants.CONFLICTS ) Optional<EntityDataModel> conflicts ) {
        this.diff = diff;
        this.conflicts = conflicts;
    }

    @JsonProperty( SerializationConstants.DIFF )
    public EntityDataModel getDiff() {
        return diff;
    }

    @JsonProperty( SerializationConstants.CONFLICTS )
    public Optional<EntityDataModel> getConflicts() {
        return conflicts;
    }

}
