package com.dataloom.data.requests;

import java.util.Map;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.data.EntityKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.SetMultimap;

public class DataCreation {
    private EntityKey                       key;
    private Optional<EntityKey>             src;
    private Optional<EntityKey>             dst;
    private SetMultimap<UUID, Object>       details;
    private Map<UUID, EdmPrimitiveTypeKind> authorizedPropertiesWithDataType;

    @JsonCreator
    public DataCreation(
            @JsonProperty( SerializationConstants.KEY_FIELD ) EntityKey key,
            @JsonProperty( SerializationConstants.SRC ) Optional<EntityKey> src,
            @JsonProperty( SerializationConstants.DEST ) Optional<EntityKey> dst,
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) SetMultimap<UUID, Object> details,
            @JsonProperty( SerializationConstants.PROPERTY_TYPES ) Map<UUID, EdmPrimitiveTypeKind> authorizedPropertiesWithDataType ) {
        this.key = key;
        this.src = src;
        this.dst = dst;
        this.details = details;
        this.authorizedPropertiesWithDataType = authorizedPropertiesWithDataType;
    }

    public DataCreation(
            EntityKey key,
            SetMultimap<UUID, Object> details,
            Map<UUID, EdmPrimitiveTypeKind> authorizedPropertyTypesWithDataType ) {
        this( key, Optional.absent(), Optional.absent(), details, authorizedPropertyTypesWithDataType );
    }

    @JsonProperty( SerializationConstants.KEY_FIELD )
    public EntityKey getKey() {
        return key;
    }

    @JsonProperty( SerializationConstants.SRC )
    public Optional<EntityKey> getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DEST )
    public Optional<EntityKey> getDst() {
        return dst;
    }

    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    public SetMultimap<UUID, Object> getDetails() {
        return details;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPES )
    public Map<UUID, EdmPrimitiveTypeKind> getAuthorizedPropertiesWithDataType() {
        return authorizedPropertiesWithDataType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ( ( authorizedPropertiesWithDataType == null ) ? 0 : authorizedPropertiesWithDataType.hashCode() );
        result = prime * result + ( ( details == null ) ? 0 : details.hashCode() );
        result = prime * result + ( ( dst == null ) ? 0 : dst.hashCode() );
        result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
        result = prime * result + ( ( src == null ) ? 0 : src.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        DataCreation other = (DataCreation) obj;
        if ( authorizedPropertiesWithDataType == null ) {
            if ( other.authorizedPropertiesWithDataType != null ) return false;
        } else if ( !authorizedPropertiesWithDataType.equals( other.authorizedPropertiesWithDataType ) ) return false;
        if ( details == null ) {
            if ( other.details != null ) return false;
        } else if ( !details.equals( other.details ) ) return false;
        if ( dst == null ) {
            if ( other.dst != null ) return false;
        } else if ( !dst.equals( other.dst ) ) return false;
        if ( key == null ) {
            if ( other.key != null ) return false;
        } else if ( !key.equals( other.key ) ) return false;
        if ( src == null ) {
            if ( other.src != null ) return false;
        } else if ( !src.equals( other.src ) ) return false;
        return true;
    }

}
