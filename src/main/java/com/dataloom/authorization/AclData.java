package com.dataloom.authorization;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AclData {
    private final Acl    acl;
    private final Action action;

    @JsonCreator
    public AclData(
            @JsonProperty( SerializationConstants.ACL ) Acl acl,
            @JsonProperty( SerializationConstants.ACTION ) Action action ) {
        this.acl = acl;
        this.action = action;
    }

    public Acl getAcl() {
        return acl;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( acl == null ) ? 0 : acl.hashCode() );
        result = prime * result + ( ( action == null ) ? 0 : action.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AclData other = (AclData) obj;
        if ( acl == null ) {
            if ( other.acl != null ) return false;
        } else if ( !acl.equals( other.acl ) ) return false;
        if ( action != other.action ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "AclData [acl=" + acl + ", action=" + action + "]";
    }

}
