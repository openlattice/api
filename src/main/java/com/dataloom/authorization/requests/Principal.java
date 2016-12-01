package com.dataloom.authorization.requests;

import java.io.Serializable;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Principal implements Serializable {
    
    @JsonProperty( SerializationConstants.TYPE_FIELD )
    private PrincipalType type;
    @JsonProperty( SerializationConstants.NAME_FIELD )
    private String id;

    @JsonCreator
    public Principal( 
            @JsonProperty( SerializationConstants.TYPE_FIELD ) PrincipalType type, 
            @JsonProperty( SerializationConstants.NAME_FIELD ) String id ){
        this.type = type;
        this.id = id;
    }
    
    public PrincipalType getType() {
        return type;
    }

    public void setType( PrincipalType type ) {
        this.type = type;
    }

    public String getName() {
        return id;
    }

    public void setName( String name ) {
        this.id = name;
    }

    @Override
    public int hashCode() {
        int result = ( type == null ) ? 0 : type.hashCode();
        result = 31 * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) 
            return true;
        if ( obj == null ) 
            return false;
        if ( getClass() != obj.getClass() ) 
            return false;

        Principal that = (Principal) obj;

        if ( type != null ? !type.equals( that.type ) : that.type != null )
            return false;
        return id != null ? id.equals( that.id ) : that.id == null;
    }

    @Override
    public String toString() {
        return "Principal [type=" + type + ", name=" + id + "]";
    }
}
