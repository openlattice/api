package com.dataloom.edm.requests;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

public class EdmRequest {
    public static enum Action {
        ADD,
        REMOVE,
        REPLACE
    };
    
    private final Action action;
    private final Set<FullQualifiedName> fqns;
    
    public EdmRequest( Action action, Set<FullQualifiedName> fqns ) {
        this.action = action;
        this.fqns = fqns;
    }
    
    public Action getAction() {
        return action;
    }
    
    public Set<FullQualifiedName> getFqns() {
        return fqns;
    }
}
