package com.dataloom.data.requests;

import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.data.EntityKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;

public class Association {
    private EntityKey                 key;
    private EntityKey                 src;
    private EntityKey                 dst;
    // This is the actual values of the LinkSet, which can be thought of as "association details" of this association
    private SetMultimap<UUID, Object> details;

    @JsonCreator
    public Association(
            @JsonProperty( SerializationConstants.KEY_FIELD ) EntityKey key,
            @JsonProperty( SerializationConstants.SRC ) EntityKey src,
            @JsonProperty( SerializationConstants.DEST ) EntityKey dst,
            @JsonProperty( SerializationConstants.DETAILS_FIELD ) SetMultimap<UUID, Object> details ) {
        this.key = key;
        this.src = src;
        this.dst = dst;
        this.details = details;
    }

    @JsonProperty( SerializationConstants.KEY_FIELD )
    public EntityKey getKey() {
        return key;
    }

    @JsonProperty( SerializationConstants.SRC )
    public EntityKey getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DEST )
    public EntityKey getDst() {
        return dst;
    }

    @JsonProperty( SerializationConstants.DETAILS_FIELD )
    public SetMultimap<UUID, Object> getDetails() {
        return details;
    }
}
