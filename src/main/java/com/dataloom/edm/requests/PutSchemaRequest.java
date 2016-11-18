package com.dataloom.edm.requests;

import com.dataloom.edm.EdmApi;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yao on 9/26/16.
 */
public class PutSchemaRequest {
    private final String namespace;
    private final String name;

    @JsonCreator
    public PutSchemaRequest(
            @JsonProperty( EdmApi.NAMESPACE ) String namespace,
            @JsonProperty( EdmApi.NAME ) String name ) {
        this.namespace = namespace;
        this.name = name;
    }

    @JsonProperty( EdmApi.NAMESPACE )
    public String getNamespace() {
        return namespace;
    }

    @JsonProperty( EdmApi.NAME )
    public String getName() {
        return name;
    }

}
