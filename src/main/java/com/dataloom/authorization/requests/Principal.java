package com.dataloom.authorization.requests;

import java.io.Serializable;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Principal implements Serializable {
    private static final long serialVersionUID = 3227310509765475747L;

    private final PrincipalType type;
    
    private final String id;
    
    @JsonCreator
    public Principal( 
            @JsonProperty( SerializationConstants.TYPE_FIELD ) PrincipalType type, 
            @JsonProperty( SerializationConstants.NAME_FIELD ) String id ){
        this.type = type;
        this.id = id;
    }
    
    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public PrincipalType getType() {
        return type;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public String getId() {
        return id;
    }
}
