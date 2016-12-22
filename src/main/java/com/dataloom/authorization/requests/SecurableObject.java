package com.dataloom.authorization.requests;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.SecurableObjectType;
import com.fasterxml.jackson.annotation.JsonCreator;

public class SecurableObject {
    private final SecurableObjectType type;
    private final FullQualifiedName   fqn;
    private final String              name;

    @JsonCreator
    public SecurableObject( SecurableObjectType type, FullQualifiedName fqn, String name ) {
        this.type = type;
        this.fqn = fqn;
        this.name = name;
    }

    public SecurableObjectType getType() {
        return type;
    }

    public FullQualifiedName getFqn() {
        return fqn;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( fqn == null ) ? 0 : fqn.hashCode() );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        SecurableObject other = (SecurableObject) obj;
        if ( fqn == null ) {
            if ( other.fqn != null ) return false;
        } else if ( !fqn.equals( other.fqn ) ) return false;
        if ( name == null ) {
            if ( other.name != null ) return false;
        } else if ( !name.equals( other.name ) ) return false;
        if ( type != other.type ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "SecurableObject [type=" + type + ", fqn=" + fqn + ", name=" + name + "]";
    }

}
