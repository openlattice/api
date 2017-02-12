package com.dataloom.data;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Uniquely identifies a version of an entity
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class EntityKey implements Comparable<EntityKey>{
    private final UUID syncId;
    private final UUID entitySetId;
    private final String entityId;

    @JsonCreator
    public EntityKey(
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId,
            @JsonProperty( SerializationConstants.ENTITY_SET_ID ) UUID entitySetId,
            @JsonProperty( SerializationConstants.ENTITY_ID ) String entityId ) {
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
    public String getEntityId() {
        return entityId;
    }

    @Override public int compareTo( EntityKey o ) {
        int result =  syncId.compareTo( o.syncId );

        if( result == 0 ) {
            result = entitySetId.compareTo( o.entitySetId );
        }

        if( result == 0 ) {
            result = entityId.compareTo( o.entityId );
        }

        return result;
    }
}
