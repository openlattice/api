package com.dataloom.data.requests;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.dataloom.data.internal.Entity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yao on 9/20/16.
 */
public class CreateEntityRequest {
    private final UUID              syncId;
    private final UUID              entitySetId;
    private final Set<Entity>       entities;

    @JsonCreator
    public CreateEntityRequest(
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId,
            @JsonProperty( SerializationConstants.ENTITY_SET_ID ) UUID entitySetId,
            @JsonProperty( SerializationConstants.ENTITIES ) Set<Entity> entities ) {
        this.syncId = checkNotNull( syncId );
        this.entitySetId = checkNotNull( entitySetId );
        this.entities = checkNotNull( entities );
    }

    @JsonProperty( SerializationConstants.SYNC_ID )
    public UUID getSyncId() {
        return syncId;
    }

    @JsonProperty( SerializationConstants.ENTITY_SET_ID )
    public UUID getEntitySetId() {
        return entitySetId;
    }

    @JsonProperty( SerializationConstants.ENTITIES )
    public Set<Entity> getEntities() {
        return entities;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof CreateEntityRequest ) ) {
            return false;
        }
        CreateEntityRequest other = (CreateEntityRequest) obj;
        if ( entitySetId == null ) {
            if ( other.entitySetId != null ) {
                return false;
            }
        } else if ( !entitySetId.equals( other.entitySetId ) ) {
            return false;
        }
        if ( entities == null ) {
            if ( other.entities != null ) {
                return false;
            }
        } else if ( !entities.equals( other.entities ) ) {
            return false;
        }
        if ( syncId == null ) {
            if ( other.syncId != null ) {
                return false;
            }
        } else if ( !syncId.equals( other.syncId ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( entitySetId == null ) ? 0 : entitySetId.hashCode() );
        result = prime * result + ( ( entities == null ) ? 0 : entities.hashCode() );
        result = prime * result + ( ( syncId == null ) ? 0 : syncId.hashCode() );
        return result;
    }

    @Override
    public String toString() {
        return "CreateEntityRequest [syncId=" + syncId + ", entitySetId=" + entitySetId + ", entities=" + entities + "]";
    }

}
