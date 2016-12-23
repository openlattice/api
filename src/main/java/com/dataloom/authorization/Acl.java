package com.dataloom.authorization;

import java.util.List;

public class Acl {
    protected final List<AclKey>  aclKey;
    protected final Iterable<Ace> aces;

    public Acl( List<AclKey> aclKey, Iterable<Ace> aces ) {
        this.aclKey = aclKey;
        this.aces = aces;
    }

    public List<AclKey> getAclKey() {
        return aclKey;
    }

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
