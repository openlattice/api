package com.openlattice.data.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;
import com.openlattice.client.serialization.SerializationConstants;

import java.util.UUID;

public class AssociationRequest {

    private final UUID                      src;
    private final UUID                      dst;
    private final UUID                      entityKeyId;
    private final SetMultimap<UUID, Object> details;

    @JsonCreator
    public AssociationRequest(
            @JsonProperty( SerializationConstants.SRC ) UUID src,
            @JsonProperty( SerializationConstants.DST ) UUID dst,
            @JsonProperty( SerializationConstants.ID_FIELD ) UUID entityKeyId,
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) SetMultimap<UUID, Object> details ) {
        this.src = src;
        this.dst = dst;
        this.entityKeyId = entityKeyId;
        this.details = details;
    }

    @JsonProperty( SerializationConstants.SRC )
    public UUID getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DST )
    public UUID getDst() {
        return dst;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getEntityKeyId() {
        return entityKeyId;
    }

    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    public SetMultimap<UUID, Object> getDetails() {
        return details;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        AssociationRequest that = (AssociationRequest) o;

        if ( src != null ? !src.equals( that.src ) : that.src != null )
            return false;
        if ( dst != null ? !dst.equals( that.dst ) : that.dst != null )
            return false;
        if ( entityKeyId != null ? !entityKeyId.equals( that.entityKeyId ) : that.entityKeyId != null )
            return false;
        return details != null ? details.equals( that.details ) : that.details == null;
    }

    @Override
    public int hashCode() {
        int result = src != null ? src.hashCode() : 0;
        result = 31 * result + ( dst != null ? dst.hashCode() : 0 );
        result = 31 * result + ( entityKeyId != null ? entityKeyId.hashCode() : 0 );
        result = 31 * result + ( details != null ? details.hashCode() : 0 );
        return result;
    }
}
