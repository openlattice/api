package com.dataloom.edm.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EdmDetailsSelector {
    private SecurableObjectType      type;
    private UUID                     id;
    private Set<SecurableObjectType> includedFields;

    @JsonCreator
    public EdmDetailsSelector(
            @JsonProperty( SerializationConstants.TYPE_FIELD ) SecurableObjectType type,
            @JsonProperty( SerializationConstants.ID_FIELD ) UUID id,
            @JsonProperty( SerializationConstants.INCLUDE_FIELD ) Set<SecurableObjectType> includedFields ) {
        this.type = type;
        this.id = id;
        this.includedFields = includedFields;
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public SecurableObjectType getType() {
        return type;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return id;
    }
    
    @JsonProperty( SerializationConstants.INCLUDE_FIELD )
    public Set<SecurableObjectType> getIncludedFields() {
        return includedFields;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( includedFields == null ) ? 0 : includedFields.hashCode() );
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        EdmDetailsSelector other = (EdmDetailsSelector) obj;
        if ( id == null ) {
            if ( other.id != null ) return false;
        } else if ( !id.equals( other.id ) ) return false;
        if ( includedFields == null ) {
            if ( other.includedFields != null ) return false;
        } else if ( !includedFields.equals( other.includedFields ) ) return false;
        if ( type != other.type ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "EdmDetailsSelector [type=" + type + ", id=" + id + ", includedFields=" + includedFields + "]";
    }

}
