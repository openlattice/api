package com.dataloom.authorization;

import java.util.List;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Acl {
    protected final List<AclKeyPathFragment>  aclKey;
    protected final Iterable<Ace> aces;

    @JsonCreator
    public Acl( 
            @JsonProperty( SerializationConstants.ACL_OBJECT_PATH ) List<AclKeyPathFragment> aclKey, 
            @JsonProperty( SerializationConstants.ACES ) Iterable<Ace> aces ) {
        this.aclKey = aclKey;
        this.aces = aces;
    }

    @JsonProperty( SerializationConstants.ACL_OBJECT_PATH ) 
    public List<AclKeyPathFragment> getAclKey() {
        return aclKey;
    }

    @JsonProperty( SerializationConstants.ACES ) 
    public Iterable<Ace> getAces() {
        return aces;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aces == null ) ? 0 : aces.hashCode() );
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Acl other = (Acl) obj;
        if ( aces == null ) {
            if ( other.aces != null ) return false;
        } else if ( !aces.equals( other.aces ) ) return false;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) return false;
        } else if ( !aclKey.equals( other.aclKey ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Acl [aclKey=" + aclKey + ", aces=" + aces + "]";
    }

}
