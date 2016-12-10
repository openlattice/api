package com.dataloom.data.requests;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

/**
 * Created by yao on 9/20/16.
 */
public class CreateEntityRequest {
    private final UUID                                        syncId;
    private final String                                      entitySetName;
    private final FullQualifiedName                           entityType;
    private final Set<SetMultimap<FullQualifiedName, Object>> propertyValues;

    @JsonCreator
    public CreateEntityRequest(
            @JsonProperty( SerializationConstants.ENTITY_SET_NAME ) String entitySetName,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName entityType,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<SetMultimap<FullQualifiedName, Object>> propertyValues,
            @JsonProperty( SerializationConstants.SYNC_ID ) UUID syncId ) {
        this.syncId = syncId;
        this.entitySetName = entitySetName;
        this.entityType = entityType;
        this.propertyValues = propertyValues;
    }

    @JsonProperty( SerializationConstants.SYNC_ID )
    public UUID getSyncId() {
        return syncId;
    }

    @JsonProperty( SerializationConstants.ENTITY_SET_NAME )
    public String getEntitySetName() {
        return entitySetName;
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getEntityType() {
        return entityType;
    }

    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Set<SetMultimap<FullQualifiedName, Object>> getPropertyValues() {
        return propertyValues;
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
        if ( entitySetName == null ) {
            if ( other.entitySetName != null ) {
                return false;
            }
        } else if ( !entitySetName.equals( other.entitySetName ) ) {
            return false;
        }
        if ( entityType == null ) {
            if ( other.entityType != null ) {
                return false;
            }
        } else if ( !entityType.equals( other.entityType ) ) {
            return false;
        }
        if ( propertyValues == null ) {
            if ( other.propertyValues != null ) {
                return false;
            }
        } else if ( !propertyValues.equals( other.propertyValues ) ) {
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
        result = prime * result + ( ( entitySetName == null ) ? 0 : entitySetName.hashCode() );
        result = prime * result + ( ( entityType == null ) ? 0 : entityType.hashCode() );
        result = prime * result + ( ( propertyValues == null ) ? 0 : propertyValues.hashCode() );
        result = prime * result + ( ( syncId == null ) ? 0 : syncId.hashCode() );
        return result;
    }

    @Override
    public String toString() {
        return "CreateEntityRequest [syncId=" + syncId + ", entitySetName=" + entitySetName + ", entityType="
                + entityType + ", propertyValues=" + propertyValues + "]";
    }

}
