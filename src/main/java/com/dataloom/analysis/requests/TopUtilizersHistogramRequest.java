package com.dataloom.analysis.requests;

import java.util.List;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class TopUtilizersHistogramRequest {
    private final List<TopUtilizerDetails> topUtilizerDetails;
    private final UUID                     primaryEntityTypeId;
    private final UUID                     primaryPropertyTypeId;
    private final Optional<UUID>           drillDownEntityTypeId;
    private final Optional<UUID>           drillDownPropertyTypeId;

    @JsonCreator
    public TopUtilizersHistogramRequest(
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) List<TopUtilizerDetails> topUtilizerDetails,
            @JsonProperty( SerializationConstants.ENTITY_TYPE_ID_FIELD ) UUID primaryEntityTypeId,
            @JsonProperty( SerializationConstants.PROPERTY_TYPE_ID ) UUID primaryPropertyTypeId,
            @JsonProperty( SerializationConstants.DRILL_DOWN_ENTITY_TYPE_ID ) Optional<UUID> drillDownEntityTypeId,
            @JsonProperty( SerializationConstants.DRILL_DOWN_PROPERTY_TYPE_ID ) Optional<UUID> drillDownPropertyTypeId ) {
        this.topUtilizerDetails = topUtilizerDetails;
        this.primaryEntityTypeId = primaryEntityTypeId;
        this.primaryPropertyTypeId = primaryPropertyTypeId;
        this.drillDownEntityTypeId = drillDownEntityTypeId;
        this.drillDownPropertyTypeId = drillDownPropertyTypeId;
    }
    
    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    public List<TopUtilizerDetails> getTopUtilizerDetails() {
        return topUtilizerDetails;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE_ID_FIELD )
    public UUID getPrimaryEntityTypeId() {
        return primaryEntityTypeId;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPE_ID )
    public UUID getPrimaryPropertyTypeId() {
        return primaryPropertyTypeId;
    }

    @JsonProperty( SerializationConstants.DRILL_DOWN_ENTITY_TYPE_ID )
    public Optional<UUID> getDrillDownEntityTypeId() {
        return drillDownEntityTypeId;
    }

    @JsonProperty( SerializationConstants.DRILL_DOWN_PROPERTY_TYPE_ID )
    public Optional<UUID> getDrillDownPropertyTypeId() {
        return drillDownPropertyTypeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( drillDownEntityTypeId == null ) ? 0 : drillDownEntityTypeId.hashCode() );
        result = prime * result + ( ( drillDownPropertyTypeId == null ) ? 0 : drillDownPropertyTypeId.hashCode() );
        result = prime * result + ( ( primaryEntityTypeId == null ) ? 0 : primaryEntityTypeId.hashCode() );
        result = prime * result + ( ( primaryPropertyTypeId == null ) ? 0 : primaryPropertyTypeId.hashCode() );
        result = prime * result + ( ( topUtilizerDetails == null ) ? 0 : topUtilizerDetails.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        TopUtilizersHistogramRequest other = (TopUtilizersHistogramRequest) obj;
        if ( drillDownEntityTypeId == null ) {
            if ( other.drillDownEntityTypeId != null ) return false;
        } else if ( !drillDownEntityTypeId.equals( other.drillDownEntityTypeId ) ) return false;
        if ( drillDownPropertyTypeId == null ) {
            if ( other.drillDownPropertyTypeId != null ) return false;
        } else if ( !drillDownPropertyTypeId.equals( other.drillDownPropertyTypeId ) ) return false;
        if ( primaryEntityTypeId == null ) {
            if ( other.primaryEntityTypeId != null ) return false;
        } else if ( !primaryEntityTypeId.equals( other.primaryEntityTypeId ) ) return false;
        if ( primaryPropertyTypeId == null ) {
            if ( other.primaryPropertyTypeId != null ) return false;
        } else if ( !primaryPropertyTypeId.equals( other.primaryPropertyTypeId ) ) return false;
        if ( topUtilizerDetails == null ) {
            if ( other.topUtilizerDetails != null ) return false;
        } else if ( !topUtilizerDetails.equals( other.topUtilizerDetails ) ) return false;
        return true;
    }

}
