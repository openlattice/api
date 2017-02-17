package com.dataloom.data;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Uniquely identifies a version of an entity in an entity set.
 *
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class EntityKey implements Comparable<EntityKey> {
    private final UUID   entitySetId;
    private final String entityId;

    @JsonCreator
    public EntityKey(
            @JsonProperty( SerializationConstants.ENTITY_SET_ID ) UUID entitySetId,
            @JsonProperty( SerializationConstants.ENTITY_ID ) String entityId ) {
        this.entitySetId = checkNotNull( entitySetId );
        this.entityId = checkNotNull( entityId );
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

        if ( !entitySetId.equals( entityKey.entitySetId ) )
            return false;
        return entityId.equals( entityKey.entityId );
    }

    @Override public int hashCode() {
        int result = entitySetId.hashCode();
        result = 31 * result + entityId.hashCode();
        return result;
    }

    @Override public int compareTo( EntityKey o ) {
        int result = entitySetId.compareTo( o.entitySetId );

        if ( result == 0 ) {
            result = entityId.compareTo( o.entityId );
        }

        return result;
    }

    @Override public String toString() {
        return "EntityKey{" +
                "entitySetId=" + entitySetId +
                ", entityId='" + entityId + '\'' +
                '}';
    }
}
