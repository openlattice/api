package com.dataloom.authorization;

import java.util.List;

import com.dataloom.authorization.requests.Action;
import com.fasterxml.jackson.annotation.JsonCreator;

public class AclData extends Acl {
    private final Action action;

    @JsonCreator
    public AclData(
            List<AclKey> aclKey,
            Iterable<Ace> aces,
            Action action ) {
        super( aclKey, aces );
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( action == null ) ? 0 : action.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( !super.equals( obj ) ) return false;
        if ( getClass() != obj.getClass() ) return false;
        AclData other = (AclData) obj;
        if ( action != other.action ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "AclData [action=" + action + ", aclKey=" + aclKey + ", aces=" + aces + "]";
    }

}
