package com.dataloom.data;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Uniquely identifies a version of an entity
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class EntityKey {
    private final UUID syncId;
    private final UUID entitySetId;
    private final UUID entityId;

    @JsonCreator
    public EntityKey(
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId,
            @JsonProperty( SerializationConstants.ENTITY_SET_ID ) UUID entitySetId,
            @JsonProperty( SerializationConstants.ENTITY_ID ) UUID entityId ) {
        this.syncId = syncId;
        this.entitySetId = entitySetId;
        this.entityId = entityId;
    }

    @JsonProperty( SerializationConstants.SYNC_ID )
    public UUID getSyncId() {
        return syncId;
    }

    @JsonProperty( SerializationConstants.ENTITY_SET_ID )
    public UUID getEntitySetId() {
        return entitySetId;
    }

    @JsonProperty( SerializationConstants.ENTITY_ID )
    public UUID getEntityId() {
        return entityId;
    }
}
