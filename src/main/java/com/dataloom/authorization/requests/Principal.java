package com.dataloom.authorization.requests;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Principal {
    
    @JsonProperty( SerializationConstants.TYPE_FIELD )
    private PrincipalType type;
    @JsonProperty( SerializationConstants.NAME_FIELD )
    private String name;

    @JsonCreator
    public Principal( 
            @JsonProperty( SerializationConstants.TYPE_FIELD ) PrincipalType type, 
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name ){
        this.type = type;
        this.name = name;
    }
    
    public PrincipalType getType() {
        return type;
    }

    public void setType( PrincipalType type ) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = ( type == null ) ? 0 : type.hashCode();
        result = 31 * result + ( ( name == null ) ? 0 : name.hashCode() );
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
        return name != null ? name.equals( that.name ) : that.name == null;
    }

    @Override
    public String toString() {
        return "Principal [type=" + type + ", name=" + name + "]";
    }
}
