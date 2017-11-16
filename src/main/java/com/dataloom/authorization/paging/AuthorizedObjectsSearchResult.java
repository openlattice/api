package com.dataloom.authorization.paging;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openlattice.authorization.AclKey;

import java.util.Set;

public class AuthorizedObjectsSearchResult {
    private String      pagingToken;
    private Set<AclKey> authorizedObjects;

    public AuthorizedObjectsSearchResult( String pagingToken, Set<AclKey> authorizedObjects ) {
        this.pagingToken = pagingToken;
        this.authorizedObjects = authorizedObjects;
    }

    @JsonProperty( SerializationConstants.PAGING_TOKEN )
    public String getPagingToken() {
        return pagingToken;
    }

    @JsonProperty( SerializationConstants.AUTHORIZED_OBJECTS )
    public Set<AclKey> getAuthorizedObjects() {
        return authorizedObjects;
    }

    @Override
    public String toString() {
        return "AuthorizedObjectsSearchResult [pagingToken=" + pagingToken + ", authorizedObjects=" + authorizedObjects
                + "]";
    }

}
