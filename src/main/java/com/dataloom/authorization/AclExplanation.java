package com.dataloom.authorization;

import java.util.List;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AclExplanation {
    protected final List<UUID>             aclKey;
    protected final Iterable<AceExplanation> aces;
    private transient int                  h = 0;

    public AclExplanation(
            List<UUID> aclKey,
            Iterable<AceExplanation> aces ) {
        this.aclKey = aclKey;
        this.aces = aces;
    }

    @JsonProperty( SerializationConstants.ACL_OBJECT_PATH )
    public List<UUID> getAclKey() {
        return aclKey;
    }

    @JsonProperty( SerializationConstants.ACES )
    public Iterable<AceExplanation> getAces() {
        return aces;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( aces == null ) ? 0 : aces.hashCode() );
            result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
            h = result;
        }
        return h;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AclExplanation other = (AclExplanation) obj;
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
        return "AclExplained [aclKey=" + aclKey + ", aces=" + aces + "]";
    }

}
