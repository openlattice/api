package com.dataloom.authorization.requests;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Principal {
    
    @JsonProperty( SerializationConstants.TYPE_FIELD )
    private PrincipalType type;
    
    @JsonProperty( SerializationConstants.ID_FIELD )
    private String id;
    
    @JsonProperty( SerializationConstants.NAME_FIELD )
    private String name;

    public Principal( 
            @JsonProperty( SerializationConstants.TYPE_FIELD ) PrincipalType type ){
        this.type = type; 
    }

    @JsonCreator
    public Principal createPrincipal(
            @JsonProperty( SerializationConstants.TYPE_FIELD ) PrincipalType type,
            @JsonProperty( SerializationConstants.ID_FIELD ) String id,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name){
        return new Principal( type ).setId( id ).setName( name );
    }
    
    public PrincipalType getType() {
        return type;
    }

    public void setType( PrincipalType type ) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Principal setId( String id ) {
        this.id = id;
        return this;
    }
    
    public String getName() {
        return name;
    }

    public Principal setName( String name ) {
        this.name = name;
        return this;
    }

    @Override
    public int hashCode() {
        int result = ( type == null ) ? 0 : type.hashCode();
        result = 31 * result + ( ( id == null ) ? 0 : id.hashCode() );
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
        if ( id != null ? !id.equals( that.id ) : that.id != null )
            return false;
        return name != null ? name.equals( that.name ) : that.name == null;
    }

    @Override
    public String toString() {
        return "Principal [type=" + type + ", id=" + id + ", name=" + name + "]";
    }

}
