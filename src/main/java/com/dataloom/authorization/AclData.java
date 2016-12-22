package com.dataloom.authorization;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dataloom.authorization.requests.Action;
import com.dataloom.authorization.requests.Permission;
import com.dataloom.authorization.requests.Principal;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Optional;

public class AclData {
    private final List<AclKey>                    aclKeys;
    private final Map<Principal, Set<Permission>> aces;
    private final Optional<Action>                action;

    @JsonCreator
    public AclData(
            List<AclKey> aclKeys,
            Map<Principal, Set<Permission>> aces,
            Optional<Action> action ) {
        this.aclKeys = aclKeys;
        this.aces = aces;
        this.action = action;
    }

    public List<AclKey> getAclKeys() {
        return aclKeys;
    }

    public Map<Principal, Set<Permission>> getAces() {
        return aces;
    }

    public Optional<Action> getAction() {
        return action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aces == null ) ? 0 : aces.hashCode() );
        result = prime * result + ( ( aclKeys == null ) ? 0 : aclKeys.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof AclData ) ) {
            return false;
        }
        AclData other = (AclData) obj;
        if ( aces == null ) {
            if ( other.aces != null ) {
                return false;
            }
        } else if ( !aces.equals( other.aces ) ) {
            return false;
        }
        if ( aclKeys == null ) {
            if ( other.aclKeys != null ) {
                return false;
            }
        } else if ( !aclKeys.equals( other.aclKeys ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AclData [aclKeys=" + aclKeys + ", aces=" + aces + "]";
    }

}
