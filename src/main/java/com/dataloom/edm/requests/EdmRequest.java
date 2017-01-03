package com.dataloom.edm.requests;

import com.dataloom.data.SerializationConstants;
import com.dataloom.edm.internal.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EdmRequest {
    public static enum Action {
        ADD,
        REMOVE,
        REPLACE
    };

    private final Action action;
    private final Schema schema;

    @JsonCreator
    public EdmRequest(
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.SCHEMA ) Schema schema ) {
        this.action = action;
        this.schema = schema;
    }

    @JsonProperty( SerializationConstants.ACTION )
    public Action getAction() {
        return action;
    }

    @JsonProperty( SerializationConstants.SCHEMA )
    public Schema getSchema() {
        return schema;
    }
}
