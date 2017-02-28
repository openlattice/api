package com.dataloom.authorization.paging;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizedObjectsSearchResult {
    private String          pagingToken;
    private Set<List<UUID>> authorizedObjects;

    public AuthorizedObjectsSearchResult( String pagingToken, Set<List<UUID>> authorizedObjects ) {
        this.pagingToken = pagingToken;
        this.authorizedObjects = authorizedObjects;
    }

    @JsonProperty( SerializationConstants.PAGING_TOKEN )
    public String getPagingToken() {
        return pagingToken;
    }

    @JsonProperty( SerializationConstants.AUTHORIZED_OBJECTS )
    public Set<List<UUID>> getAuthorizedObjects() {
        return authorizedObjects;
    }

    @Override
    public String toString() {
        return "AuthorizedObjectsSearchResult [pagingToken=" + pagingToken + ", authorizedObjects=" + authorizedObjects
                + "]";
    }

}
