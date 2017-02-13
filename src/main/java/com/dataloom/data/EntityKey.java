package com.dataloom.data;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Uniquely identifies a version of an entity
 *
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class EntityKey implements Comparable<EntityKey> {
    private final UUID   syncId;
    private final UUID   entitySetId;
    private final String entityId;

    @JsonCreator
    public EntityKey(
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId,
            @JsonProperty( SerializationConstants.ENTITY_SET_ID ) UUID entitySetId,
            @JsonProperty( SerializationConstants.ENTITY_ID ) String entityId ) {
        this.syncId = checkNotNull( syncId );
        this.entitySetId = checkNotNull( entitySetId );
        this.entityId = checkNotNull( entityId );
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

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( !( o instanceof EntityKey ) )
            return false;

        EntityKey entityKey = (EntityKey) o;

        if ( !syncId.equals( entityKey.syncId ) )
            return false;
        if ( !entitySetId.equals( entityKey.entitySetId ) )
            return false;
        return entityId.equals( entityKey.entityId );
    }

    @Override public int hashCode() {
        int result = syncId.hashCode();
        result = 31 * result + entitySetId.hashCode();
        result = 31 * result + entityId.hashCode();
        return result;
    }

    @Override public int compareTo( EntityKey o ) {
        int result = syncId.compareTo( o.syncId );

        if ( result == 0 ) {
            result = entitySetId.compareTo( o.entitySetId );
        }

        if ( result == 0 ) {
            result = entityId.compareTo( o.entityId );
        }

        return result;
    }
}
